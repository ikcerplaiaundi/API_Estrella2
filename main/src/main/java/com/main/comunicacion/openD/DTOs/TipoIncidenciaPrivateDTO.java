package com.main.comunicacion.openD.DTOs;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.main.modelo.entidades.Incidencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO de tipo de incidencia de la api interna
public class TipoIncidenciaPrivateDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;

    @OneToMany(mappedBy = "tipoIncidencia")
    @JsonBackReference
    private List<Incidencia> incidencias = new ArrayList<Incidencia>();



}
