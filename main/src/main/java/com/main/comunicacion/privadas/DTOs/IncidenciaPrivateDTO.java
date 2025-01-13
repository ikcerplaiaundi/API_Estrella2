package com.main.comunicacion.privadas.DTOs;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class IncidenciaPrivateDTO {

    private long id;
    private String latitud;
    private String longitud;
    private String causa;
    private String nivelIncidencia;
    private String carretera;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @JsonIgnore
    private long idCiudad;
    @JsonIgnore
    private long idRegion;
    @JsonIgnore
    private long idTipoIncidencia;

    private String nombreCiudad;
    private String nombreProvincia;
    private String nombreRegion;
    private String nombreTipoIncidencia;

    public IncidenciaPrivateDTO(long id, String latitud, String longitud, String causa, String nivelIncidencia,
            String carretera, Date fechaInicio) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.causa = causa;
        this.nivelIncidencia = nivelIncidencia;
        this.carretera = carretera;
        this.fechaInicio = fechaInicio;
    }

    public IncidenciaPrivateDTO(String latitud, String longitud, String causa, String nivelIncidencia,
            String carretera, Date fechaInicio) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.causa = causa;
        this.nivelIncidencia = nivelIncidencia;
        this.carretera = carretera;
        this.fechaInicio = fechaInicio;
    }

    public IncidenciaPrivateDTO(long id, String latitud, String longitud, String causa, String nivelIncidencia,
            String carretera, Date fechaInicio, long idCiudad, long idRegion, long idTipoIncidencia) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.causa = causa;
        this.nivelIncidencia = nivelIncidencia;
        this.carretera = carretera;
        this.fechaInicio = fechaInicio;
        this.idCiudad = idCiudad;
        this.idRegion = idRegion;
        this.idTipoIncidencia = idTipoIncidencia;
    }

    public IncidenciaPrivateDTO(long id, String latitud, String longitud, String causa, String nivelIncidencia,
            String carretera, Date fechaInicio, String nombreCiudad,String nombreProvincia ,String nombreRegion, String nombreTipoIncidencia) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.causa = causa;
        this.nivelIncidencia = nivelIncidencia;
        this.carretera = carretera;
        this.fechaInicio = fechaInicio;
        this.nombreCiudad = nombreCiudad;
        this.nombreProvincia = nombreProvincia;
        this.nombreRegion = nombreRegion;
        this.nombreTipoIncidencia = nombreTipoIncidencia;
    }


    
    public IncidenciaPrivateDTO() {
    }
}
