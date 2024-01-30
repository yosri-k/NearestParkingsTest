package com.nearestParks.nearestParks.dto.available;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AvailableParkingResponse {

    private int total;
    private String next;
    private List<AvailableParkingDto> results;


}
