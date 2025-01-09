package com.main.comunicacion.openD.DTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaDTO {
    @JsonProperty("place_id")
    private long placeId;

    private String licence;
    
    @JsonProperty("osm_type")
    private String osmType;
    
    @JsonProperty("osm_id")
    private long osmId;

    private String lat;
    private String lon;

    
    @JsonProperty("class")
    private String classType;

    private String type;

    @JsonProperty("place_rank")
    private int placeRank;

    private double importance;

    @JsonProperty("addresstype")
    private String addressType;

    private String name;

    @JsonProperty("display_name")
    private String displayName;

    private List<String> boundingbox;
}
