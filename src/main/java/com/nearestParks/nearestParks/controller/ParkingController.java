package com.nearestParks.nearestParks.controller;


import com.nearestParks.nearestParks.dto.available.AvailableParkingDto;
import com.nearestParks.nearestParks.services.services.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    private final ParkingService parkingService;



    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }


    /**
     * Endpoint REST pour récupérer la liste des parkings triés par proximité
     * en fonction des coordonnées géographiques fournies (latitude et longitude).
     *
     * @param latitude La latitude de la position actuelle.
     * @param longitude La longitude de la position actuelle.
     * @return Une liste de type AvailableParkingDto triée par proximité.
     *
     * Exemple d'utilisation:
     * GET /nearest?latitude=xx.xxxx&longitude=yy.yyyy
     */
    @GetMapping("/nearest")
    public List <AvailableParkingDto> trierParkingsParDistance(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {
        return parkingService.getAllParkingWithProximity(latitude, longitude);

    }

}
