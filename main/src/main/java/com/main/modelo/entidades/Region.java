package com.main.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "regiones")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_region")
    private Long idRegion;

    @Column(name = "nombre_es")
    private String nombreEs;

    @Column(name = "nombre_eu")
    private String nombreEu;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Camera> cameras = new ArrayList<>();

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Incidencia> incidencias = new ArrayList<>();

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Long getIdRegion() {
        return idRegion;
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
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
}
