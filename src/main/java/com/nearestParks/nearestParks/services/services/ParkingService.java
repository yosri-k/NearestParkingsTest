package com.nearestParks.nearestParks.services.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.nearestParks.nearestParks.dto.available.AvailableParkingDto;
import com.nearestParks.nearestParks.dto.occupancy.OccupancyParkingResponse;
import reactor.core.publisher.Mono;
import java.util.List;

public interface ParkingService {
    public Mono<OccupancyParkingResponse> getExternelApiForOccupancyParking();
    List<AvailableParkingDto> getAllParkingWithProximity(double latitude, double longitude);
    public Mono<OccupancyParkingResponse> mapJsonToOccupancyParkingResponse(JsonNode jsonResponse);
    public List<AvailableParkingDto> ordredByDistance(List<AvailableParkingDto> parkings, double latitude, double longitude);
    public double calculateDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB);

}
