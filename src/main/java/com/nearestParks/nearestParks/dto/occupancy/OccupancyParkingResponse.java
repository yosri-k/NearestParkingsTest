package com.nearestParks.nearestParks.dto.occupancy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OccupancyParkingResponse {

    private int total;
    private String next;
    private List<OccupancyParkingDto> results;

}
