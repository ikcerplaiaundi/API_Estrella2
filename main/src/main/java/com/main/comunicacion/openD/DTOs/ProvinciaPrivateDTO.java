package com.main.comunicacion.openD.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Entida provincia
@Data
public class ProvinciaPrivateDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    private String latitud;
    private String longitud;

    @OneToMany(mappedBy = "provincia")
    @JsonBackReference
    private List<CiudadPrivateDTO> ciudades = new ArrayList<CiudadPrivateDTO>();
    //https://nominatim.openstreetmap.org/search?q=Buenos+Aires+Province&format=json



}
