package com.nearestParks.nearestParks.services.servicesImpl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nearestParks.nearestParks.dto.available.AvailableParkingResponse;
import com.nearestParks.nearestParks.dto.available.AvailableParkingDto;
import com.nearestParks.nearestParks.dto.occupancy.OccupancyParkingDto;
import com.nearestParks.nearestParks.dto.occupancy.OccupancyParkingResponse;
import com.nearestParks.nearestParks.exceptions.FailedToDeserializeJsonException;
import com.nearestParks.nearestParks.exceptions.IllegalAtitudeException;
import com.nearestParks.nearestParks.exceptions.IllegalLongituteException;
import com.nearestParks.nearestParks.exceptions.constants.ExceptionConstants;
import com.nearestParks.nearestParks.services.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static com.nearestParks.nearestParks.constants.Uris.*;


@Service
public class ParkingServiceImpl implements ParkingService {

    private final WebClient webClientApi;

    @Autowired
    public ParkingServiceImpl(WebClient.Builder webCientBuilder) {
        this.webClientApi = webCientBuilder.baseUrl(BASE_URL).build();

    }

    /**
     * Appel à une API externe pour récupérer les informations d'occupation des parkings.
     * Utilise WebClient pour effectuer une requête GET à l'URL spécifiée.
     * @return Un Mono contenant la réponse désérialisée sous forme d'objet
     * OccupancyParkingResponse représentant l'occupation des parkings.
     */
    public Mono<OccupancyParkingResponse> getExternelApiForOccupancyParking() {
        return webClientApi.get()
                .uri(OCCUPANCY_PARKINGS_URL)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMap(this::mapJsonToOccupancyParkingResponse)
                .onErrorResume(e -> Mono.error(new FailedToDeserializeJsonException(ExceptionConstants.FAILED_TO_DESERIALIZE_JSON)));
    }

    /**
     * Méthode pour mapper une réponse JSON en un objet OccupancyParkingResponse.
     * @param jsonResponse La chaîne JSON représentant les informations d'occupation des parkings.
     * @return Un Mono contenant l'objet désérialisé OccupancyParkingResponse.
     */
    @Override
    public Mono<OccupancyParkingResponse> mapJsonToOccupancyParkingResponse(JsonNode jsonResponse) {
        return Mono.fromCallable(() -> {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.treeToValue(jsonResponse, OccupancyParkingResponse.class);
        });
    }

    /**
     * Appel à une API externe pour récupérer les informations sur la disponibilité des parkings.
     * Utilise WebClient pour effectuer une requête GET à l'URL spécifiée avec un numéro de page.
     * @param nextPage Le numéro de page pour obtenir la prochaine tranche de la liste.
     * @return Un Mono contenant la réponse désérialisée sous forme d'objet AvailableParkingResponse
     * représentant la disponibilité des parkings.
     */
    public Mono<AvailableParkingResponse> getExternelApiForAvailbleParking(int nextPage) {
        return webClientApi.get()
                .uri(AVAILABLE_PARKINGS_URL+ nextPage)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .flatMap(this::mapJsonToAvailableParkingResponse)
                .onErrorResume(e -> Mono.error(new FailedToDeserializeJsonException(ExceptionConstants.FAILED_TO_DESERIALIZE_JSON)));
    }

    /**
     * Méthode pour mapper une réponse JSON en un objet AvailableParkingResponse.
     * @param jsonResponse La chaîne JSON représentant les informations la disponibilité des parkings.
     * @return Un Mono contenant l'objet désérialisé AvailableParkingResponse.
     */
    public Mono<AvailableParkingResponse> mapJsonToAvailableParkingResponse(JsonNode jsonResponse) {
        return Mono.fromCallable(() -> {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.treeToValue(jsonResponse, AvailableParkingResponse.class);
        });
    }

    /**
     * Obtient la liste complète de parkings disponibles en fusionnant les résultats de plusieurs pages.
     * @return Une liste de AvailableParkingDto comprenant les résultats de plusieurs pages.
     * Cette méthode utilise des appels asynchrones à l'API externe pour récupérer des listes de parkings
     * disponibles à partir de différentes pages. Les résultats sont ensuite fusionnés en une seule liste.
     * La méthode bloque temporairement pour obtenir la liste fusionnée synchronement à l'aide de Mono.block().
     */
    public List<AvailableParkingDto> getAllAvailbleParkingListMerged() {
        Mono<AvailableParkingResponse> AvailbleParkingListWithPage0 = getExternelApiForAvailbleParking(0);
        Mono<AvailableParkingResponse> AvailbleParkingListWithPage12 = getExternelApiForAvailbleParking(12);
        Mono<AvailableParkingResponse> AvailbleParkingListWithPage24 = getExternelApiForAvailbleParking(24);
        Mono<List<AvailableParkingDto>> mergedAvailbleParkingMonoList = AvailbleParkingListWithPage0.flatMap(response1 ->
                AvailbleParkingListWithPage12.flatMap(response2 ->
                        AvailbleParkingListWithPage24.map(response3 -> {
                            List<AvailableParkingDto> mergedList = new ArrayList<>();
                            mergedList.addAll(response1.getResults());
                            mergedList.addAll(response2.getResults());
                            mergedList.addAll(response3.getResults());
                            return mergedList;
                        })
                )
        );
        List<AvailableParkingDto> mergedAvailbleParkingList = mergedAvailbleParkingMonoList.block();
        return mergedAvailbleParkingList;
    }

    /**
     * Cette méthode utilise des appels asynchrones à deux API externes pour récupérer des listes de parkings disponibles
     * et des informations d'occupation. Les résultats sont obtenus de manière synchronisée à l'aide de blocage temporaire,
     * puis les parkings sans informations d'occupation sont filtrés. La liste résultante est renvoyée.
     * @return Une liste de AvailableParkingDto comprenant uniquement les parkings ayant des informations d'occupation.
     */
    public List<AvailableParkingDto> getAvailableParkingListWithOccupancy() {
        Mono<List<OccupancyParkingDto>> occpancyParkingList = getExternelApiForOccupancyParking().flatMap(occupancyParkingResponse -> Mono.just(occupancyParkingResponse.getResults()));
        List<AvailableParkingDto> parkingList = getAllAvailbleParkingListMerged();
        List<OccupancyParkingDto> occupancyList = occpancyParkingList.block();
        List<AvailableParkingDto> filteredList = new ArrayList<>();
        if (parkingList != null && occupancyList != null) {
            filteredList = removeUnavailableParkings(parkingList, occupancyList);
        }
        return filteredList;
    }

    /**
     * Cette méthode utilise les streams Java pour filtrer les parkings disponibles en fonction de la présence
     * de leurs noms dans la liste des informations d'occupation. Seuls les parkings ayant des informations d'occupation
     * associées seront inclus dans la liste résultante.
     * @param parkingList    La liste des parkings disponibles.
     * @param occupancyList  La liste des informations d'occupation des parkings.
     * @return Une liste de AvailableParkingDto comprenant uniquement les parkings disponible avec des des places disponible.
     */
    public List<AvailableParkingDto> removeUnavailableParkings(List<AvailableParkingDto> parkingList, List<OccupancyParkingDto> occupancyList) {
        return parkingList.stream()
                .filter(parking -> occupancyList.stream()
                        .anyMatch(occupancy -> occupancy.getNom().equals(parking.getNom())))
                .collect(Collectors.toList());
    }

    /**
     * Obtient la liste de parkings disponibles avec des informations d'occupation,
     * triée par proximité par rapport aux coordonnées géographiques spécifiées.
     * @param latitude  La latitude de la position actuelle.
     * @param longitude La longitude de la position actuelle.
     * @return Une liste de AvailableParkingDto triée par proximité.
     */
    @Override
    public List<AvailableParkingDto> getAllParkingWithProximity(double latitude, double longitude) {
        validateLatitude(latitude);
        validateLongitude(longitude);
        List<AvailableParkingDto> filtredList = getAvailableParkingListWithOccupancy();
        return ordredByDistance(filtredList, latitude, longitude);
    }

    private void validateLatitude(double latitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalAtitudeException(ExceptionConstants.LATITUDE_MUST_BE_BETWEEN_MINUS_90_AND_90_DEGREES);
        }
    }

    private void validateLongitude(double longitude) {
        if (longitude < -180 || longitude > 180) {
            throw new IllegalLongituteException(ExceptionConstants.LONGITUDE_MUST_BE_BETWEEN_MINUS_180_AND_180_DEGREES);
        }
    }

    /**
     * Trie la liste de parkings par proximité par rapport aux coordonnées géographiques spécifiées.
     * @param parkings   La liste des parkings à trier.
     * @param latitude   La latitude de la position actuelle.
     * @param longitude  La longitude de la position actuelle.
     * @return Une liste de AvailableParkingDto triée par proximité.
     */
    public List<AvailableParkingDto> ordredByDistance(List<AvailableParkingDto> parkings, double latitude, double longitude) {
        parkings.sort((parkingSrc, parkingDest) -> {
            double distanceA = calculateDistance(latitude, longitude, parkingSrc.getYlat(), parkingSrc.getXlong());
            double distanceB = calculateDistance(latitude, longitude, parkingDest.getYlat(), parkingDest.getXlong());
            return Double.compare(distanceA, distanceB);
        });
        return parkings;
    }

    /**
     * Calcule la distance en kilomètres entre deux points géographiques (latitude, longitude) en utilisant la formule de Haversine.
     * @param latitudeA   La latitude du point A.
     * @param longitudeA  La longitude du point A.
     * @param latitudeB   La latitude du point B.
     * @param longitudeB  La longitude du point B.
     * @return La distance en kilomètres entre les deux points.
     */
    public double calculateDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        final int rayonTerre = 6371; // Rayon de la terre en Kilométres
        double distanceLatitude = Math.toRadians(latitudeB - latitudeA);
        double distanceLongitute = Math.toRadians(longitudeB - longitudeA);
        double a = Math.sin(distanceLatitude / 2) * Math.sin(distanceLatitude / 2)
                + Math.cos(Math.toRadians(latitudeA)) * Math.cos(Math.toRadians(latitudeB))
                * Math.sin(distanceLongitute / 2) * Math.sin(distanceLongitute / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return rayonTerre * c;
    }
}
