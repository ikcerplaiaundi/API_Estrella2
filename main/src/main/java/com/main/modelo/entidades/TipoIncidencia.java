package com.main.modelo.entidades;


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

//Entida tipos_incidencias
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipos_incidencias")
public class TipoIncidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;

    @OneToMany(mappedBy = "tipoIncidencia")
    @JsonBackReference
    private List<Incidencia> incidencias = new ArrayList<Incidencia>();



}
