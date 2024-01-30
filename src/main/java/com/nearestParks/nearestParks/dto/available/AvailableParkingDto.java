package com.nearestParks.nearestParks.dto.available;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableParkingDto {


    @JsonProperty("gml_id")
    private String gml_id;

    @JsonProperty("nb_velo")
    private long nb_velo ;

    @JsonProperty("nom_du_par")
    private String nom_du_par ;

    @JsonProperty("abo_non_re")
    private Boolean abo_non_re ;

    @JsonProperty("nb_pmr")
    private long nb_pmr ;

    @JsonProperty("nb_autopar")
    private long nb_autopar ;

    @JsonProperty("nom")
    private String Nom ;

    @JsonProperty("nb_2r_el")
    private boolean nb_2r_el ;

    @JsonProperty("geo_point")
    private String geo_point;


    @JsonProperty("type_ouvra")
    private String type_ouvra ;

    @JsonProperty("tarif_4h")
    private double tarif_4h ;

    @JsonProperty("f_30_mn")
    private String f_30_mn ;


    @JsonProperty("tarif_2h")
    private double tarif_2h ;

    @JsonProperty("id")
    private String id ;

    @JsonProperty("abo_reside")
    private boolean abo_reside ;

    @JsonProperty("info")
    private String info ;

    @JsonProperty("nb_voiture")
    private long nb_voiture ;

    @JsonProperty("_geopoint")
    private String _geopoint;

    @JsonProperty("geo_shape")
    private String geo_shape ;

    @JsonProperty("type_usage")
    private String type_usage ;

    @JsonProperty("nb_2_rm")
    private int nb_2_rm ;

    @JsonProperty("num_siret")
    private long num_siret ;

    @JsonProperty("_i")
    private int _i ;

    @JsonProperty("url")
    private String url;

    @JsonProperty("tarif_1h")
    private double tarif_1h ;

    @JsonProperty("f_2h15")
    private double f_2h15 ;

    @JsonProperty("_rand")
    private long  _rand ;

    @JsonProperty("insee")
    private String insee ;

    @JsonProperty("gratuit")
    private boolean gratuit ;

    @JsonProperty("nb_covoit")
    private boolean nb_covoit ;

    @JsonProperty("hauteur_ma")
    private int hauteur_ma ;

    @JsonProperty("tarif_3h")
    private double tarif_3h ;

    @JsonProperty("adresse")
    private String adresse ;

    @JsonProperty("xlong")
    private double xlong ;

    @JsonProperty("tarif_24h")
    private double tarif_24h ;

    @JsonProperty("nb_places")
    private int nb_places ;

    @JsonProperty("ylat")
    private double ylat ;

    @JsonProperty("_score")
    private Long _score ;

    @JsonProperty("_id")
    private String _id ;


    public AvailableParkingDto(String gml_id, long nb_velo, String nom_du_par, Boolean abo_non_re, long nb_pmr, long nb_autopar, String nom, boolean nb_2r_el, String geo_point, String type_ouvra, double tarif_4h, double tarif_2h, String id, boolean abo_reside, String info, long nb_voiture, String _geopoint, String geo_shape, String type_usage, int nb_2_rm, long num_siret, int _i, String url, double tarif_1h, double f_2h15, long _rand, String insee, boolean gratuit, boolean nb_covoit, int hauteur_ma, double tarif_3h, String adresse, double xlong, double tarif_24h, int nb_places, double ylat, Long _score, String _id) {
        this.gml_id = gml_id;
        this.nb_velo = nb_velo;
        this.nom_du_par = nom_du_par;
        this.abo_non_re = abo_non_re;
        this.nb_pmr = nb_pmr;
        this.nb_autopar = nb_autopar;
        Nom = nom;
        this.nb_2r_el = nb_2r_el;
        this.geo_point = geo_point;
        this.type_ouvra = type_ouvra;
        this.tarif_4h = tarif_4h;
        this.tarif_2h = tarif_2h;
        this.id = id;
        this.abo_reside = abo_reside;
        this.info = info;
        this.nb_voiture = nb_voiture;
        this._geopoint = _geopoint;
        this.geo_shape = geo_shape;
        this.type_usage = type_usage;
        this.nb_2_rm = nb_2_rm;
        this.num_siret = num_siret;
        this._i = _i;
        this.url = url;
        this.tarif_1h = tarif_1h;
        this.f_2h15 = f_2h15;
        this._rand = _rand;
        this.insee = insee;
        this.gratuit = gratuit;
        this.nb_covoit = nb_covoit;
        this.hauteur_ma = hauteur_ma;
        this.tarif_3h = tarif_3h;
        this.adresse = adresse;
        this.xlong = xlong;
        this.tarif_24h = tarif_24h;
        this.nb_places = nb_places;
        this.ylat = ylat;
        this._score = _score;
        this._id = _id;
    }

}

