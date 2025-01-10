package com.main.comunicacion.privadas.DTOs;

import java.util.Date;

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


    private long idCiudad;
    private long idRegion;
    private long idTipoIncidencia;

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
            String carretera, Date fechaInicio,  long idCiudad,long idRegion,long idTipoIncidencia) {
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

    public IncidenciaPrivateDTO() {
    }
}