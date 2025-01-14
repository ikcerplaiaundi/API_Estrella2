package com.main.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

//Entida ciudad
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ciudades")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String latitud;
    private String longitud;
    


    @ManyToOne
    @JsonBackReference
    private Provincia provincia;

    @OneToMany(mappedBy = "ciudad")
    @JsonBackReference
    private List<Incidencia> incidencias = new ArrayList<Incidencia>();
    //https://nominatim.openstreetmap.org/search?q=Mexico&format=json


    

}
