package com.nearestParks.nearestParks.services.servicesImpl;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;


@ExtendWith(MockitoExtension.class)
class ParkingServiceImplTest {

    private static MockWebServer mockWebServer;
    private static ParkingServiceImpl parkingServiceImpl;

    @BeforeAll
    static void setUp(){
        mockWebServer = new MockWebServer();
        WebClient mockedWebClient =WebClient.builder()
                .baseUrl(mockWebServer.url("https://data.grandpoitiers.fr").toString())
                .build();
        parkingServiceImpl = new ParkingServiceImpl(WebClient.builder());
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.close();
    }

/*    @Test
    void orderingParkingsByDistance_shouldBeNotEquals() {
        // Créez une liste de parkings pour le test
        List<AvailableParkingDto> parkings = new ArrayList<>();
        parkings.add(new AvailableParkingDto("Parkings.6", 0,"PASTEUR", false,2,0,"PASTEUR",false, "46.57586833632977, 0.3532536023911788", "PARKING_EN_SURFACE", 1.6,"gratuit",0.8,"F4604__1926",true,"Stationnement longue durée (Zone violet) - Pour les horaires 6H maximum application du FPS 30€ pour 6H15",0,"46.57586833632977, 0.3532536023911788","{\"coordinates\": [0.353253602391179, 46.57586833632977], \"type\": \"Point\"}","tous",0, 21860194600658L, 7,"https://www.poitiers.fr/c__246_993__Tarifs_des_parkings.html",0.4,0.9,607611,"86194",true, false,0, 1.2,"Rue Pasteur",0.3532536,30,32, 46.57586834, null,"6XXYI7TY7jfCjJThbdA7n"));
        parkings.add(new AvailableParkingDto("Parkings.21",20,"HOTEL DE VILLE",true,14,4,"HOTEL DE VILLE",false,"46.5793235337795, 0.3385507838016221","OUVRAGE",7.6,4,"F4604__1941",true,"En journée (Lundi au Samedi) au-delá de 3 heures de stationnement 0,4 € le 1/4 h supplémentaire, au-delà de 4 heures 0,3€ le 1/4 h supplémentaire - Tarif de nuit 19h á 8h 1€ - Forfait journée 10€ (Du lundi au samedi) 4€ (Dimanche et jours fériés)",10,"46.5793235337795, 0.3385507838016221","{\"coordinates\": [0.338550783801622, 46.5793235337795], \"type\": \"Point\"}","tous",16,21860194600658L,5,"https://www.poitiers.fr/c__246_993__Tarifs_des_parkings.html",2,4.5,156626,"86194",false,false,290, 6,"22 rue Carnot",0.33855078,17,625,46.57932353,null,"v58eKCT9HSvzWUUkWGUdp"));
        // Coordonnées pour trier par proximité
        double latitude = 46.5793235337795;
        double longitude = 0.3385507838016221;
        // Définissez les résultats attendus après le tri
        List<AvailableParkingDto> expectedResults = new ArrayList<>(parkings);
        expectedResults.sort((parkingSrc, parkingDest) -> {
            double distanceA = parkingServiceImpl.calculateDistance(latitude, longitude, parkingSrc.getYlat(), parkingSrc.getXlong());
            double distanceB = parkingServiceImpl.calculateDistance(latitude, longitude, parkingDest.getYlat(), parkingDest.getXlong());
            return Double.compare(distanceA, distanceB);
        });
        // Appelez la méthode à tester
        List<AvailableParkingDto> result = parkingServiceImpl.ordredByDistance(parkings, latitude, longitude);

        for(int i=0; i< expectedResults.size(); i++){
            assertNotEquals(expectedResults.get(i), result.get(i));
        }

    }*/

/*    @Test
    void orderingParkingsByDistance_shouldBeEquals() {
        List<AvailableParkingDto> parkings = new ArrayList<>();
        parkings.add(new AvailableParkingDto("Parkings.6", 0,"PASTEUR", false,2,0,"PASTEUR",false, "46.57586833632977, 0.3532536023911788", "PARKING_EN_SURFACE", 1.6,"gratuit",0.8,"F4604__1926",true,"Stationnement longue durée (Zone violet) - Pour les horaires 6H maximum application du FPS 30€ pour 6H15",0,"46.57586833632977, 0.3532536023911788","{\"coordinates\": [0.353253602391179, 46.57586833632977], \"type\": \"Point\"}","tous",0, 21860194600658L, 7,"https://www.poitiers.fr/c__246_993__Tarifs_des_parkings.html",0.4,0.9,607611,"86194",true, false,0, 1.2,"Rue Pasteur",0.3532536,30,32, 46.57586834, null,"6XXYI7TY7jfCjJThbdA7n"));
        parkings.add(new AvailableParkingDto("Parkings.21",20,"HOTEL DE VILLE",true,14,4,"HOTEL DE VILLE",false,"46.5793235337795, 0.3385507838016221","OUVRAGE",7.6,4,"F4604__1941",true,"En journée (Lundi au Samedi) au-delá de 3 heures de stationnement 0,4 € le 1/4 h supplémentaire, au-delà de 4 heures 0,3€ le 1/4 h supplémentaire - Tarif de nuit 19h á 8h 1€ - Forfait journée 10€ (Du lundi au samedi) 4€ (Dimanche et jours fériés)",10,"46.5793235337795, 0.3385507838016221","{\"coordinates\": [0.338550783801622, 46.5793235337795], \"type\": \"Point\"}","tous",16,21860194600658L,5,"https://www.poitiers.fr/c__246_993__Tarifs_des_parkings.html",2,4.5,156626,"86194",false,false,290, 6,"22 rue Carnot",0.33855078,17,625,46.57932353,null,"v58eKCT9HSvzWUUkWGUdp"));
        double latitude = 46.5793235337795;
        double longitude = 0.3385507838016221;
        List<AvailableParkingDto> expectedResults = new ArrayList<>(parkings);
        expectedResults.sort((parkingSrc, parkingDest) -> {
            double distanceA = parkingServiceImpl.calculateDistance(latitude, longitude, parkingSrc.getYlat(), parkingSrc.getXlong());
            double distanceB = parkingServiceImpl.calculateDistance(latitude, longitude, parkingDest.getYlat(), parkingDest.getXlong());
            return Double.compare(distanceA, distanceB);
        });
        List<AvailableParkingDto> result = parkingServiceImpl.ordredByDistance(parkings, latitude, longitude);
        assertNotEquals(expectedResults, result);
    }*/


}