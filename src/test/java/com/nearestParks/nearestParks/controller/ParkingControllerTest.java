package com.nearestParks.nearestParks.controller;

import com.nearestParks.nearestParks.dto.available.AvailableParkingDto;
import com.nearestParks.nearestParks.services.servicesImpl.ParkingServiceImpl;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParkingControllerTest {

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

    @Test
    void sortParkingsPerDistance_shouldReturnSortedParkings(){
        List <AvailableParkingDto> parkingsList = parkingServiceImpl.getAllParkingWithProximity(46.5793235337795, 0.3385507838016221);
        assertTrue(parkingsList.size() > 0 );
    }

}