package com.main.comunicacion.openD.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.main.modelo.entidades.Incidencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO de ciudad de la api interna
@Data
public class CiudadPrivateDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String latitud;
    private String longitud;
    


    @ManyToOne
    @JsonBackReference
    private ProvinciaPrivateDTO provincia;

    @OneToMany(mappedBy = "ciudad")
    @JsonBackReference
    private List<IncidenciaPrivateDTO> incidencias = new ArrayList<IncidenciaPrivateDTO>();
    //https://nominatim.openstreetmap.org/search?q=Mexico&format=json


    

}
