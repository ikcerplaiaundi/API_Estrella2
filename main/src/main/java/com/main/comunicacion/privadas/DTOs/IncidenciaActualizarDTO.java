package com.main.comunicacion.privadas.DTOs;

import java.util.Date;
import lombok.Data;

@Data
public class IncidenciaActualizarDTO {

    private long id;
    private String latitud;
    private String longitud;
    private String causa;
    private String nivelIncidencia;
    private String carretera;
    private Date fechaInicio;
    private long idCiudad;  // Solo ID de ciudad
    private long idRegion;  // Solo ID de región
    private long idTipoIncidencia;  // Solo ID de tipo de incidencia

    public IncidenciaActualizarDTO() {}

    public IncidenciaActualizarDTO(long id, String latitud, String longitud, String causa, String nivelIncidencia,
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
}
