package com.main.modelo.entidades;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "regiones")
public class Region {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombre_es")
    private String nombreEs;

    @Column(name = "nombre_eu")
    private String nombreEu;

    @OneToMany(mappedBy = "cameras")
    List<Camera> Cameras = new ArrayList<>();
    
    @OneToMany(mappedBy = "incidencias")
    List<Incidencia> Incidencias = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEs() {
        return nombreEs;
    }

    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs;
    }

    public String getNombreEu() {
        return nombreEu;
    }

    public void setNombreEu(String nombreEu) {
        this.nombreEu = nombreEu;
    }

    public List<Camera> getCameras() {
        return Cameras;
    }

    public void setCameras(List<Camera> cameras) {
        Cameras = cameras;
    }

    public List<Incidencia> getIncidencias() {
        return Incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        Incidencias = incidencias;
    }
    
    
}