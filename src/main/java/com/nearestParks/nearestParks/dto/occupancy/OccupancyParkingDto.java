package com.nearestParks.nearestParks.dto.occupancy;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccupancyParkingDto {


      @JsonProperty("_rand")
      private int _rand;

      @JsonProperty("_infos_parkings.geo_point")
      private String infosParkingsGeoPoint;

      @JsonProperty("_geopoint")
      private String _geopoint ;

      @JsonProperty("Capacite")
      private int Capacite ;

      @JsonProperty("_i")
      private int _i ;

      @JsonProperty("Id")
      private int Id ;

      @JsonProperty("Nom")
      private String Nom ;

      @JsonProperty("taux_doccupation")
      private BigDecimal taux_doccupation ;

      @JsonProperty("Places")
      private int Places ;

      @JsonProperty("_score")
      private int _score ;

      @JsonProperty("_id")
      private String _id ;

      @JsonProperty("Dernière_mise_à_jour_Base")
      private String Dernière_mise_à_jour_Base ;

      @JsonProperty("Dernière_actualisation_BO")
      private String Dernière_actualisation_BO ;

}
