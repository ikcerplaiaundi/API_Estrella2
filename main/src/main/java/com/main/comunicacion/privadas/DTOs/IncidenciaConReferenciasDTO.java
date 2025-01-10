package com.main.comunicacion.privadas.DTOs;

import java.util.Date;
import lombok.Data;

@Data
public class IncidenciaConReferenciasDTO {

    private long id;
    private String latitud;
    private String longitud;
    private String causa;
    private String nivelIncidencia;
    private String carretera;
    private Date fechaInicio;
    private IncidenciaCiudadPrivateDTO ciudad;  // DTO para ciudad
    private IncidenciaRegionPrivateDTO region;   // DTO para región
    private IncidenciaTipoIncidenciaPrivateDTO tipoIncidencia;  // DTO para tipoIncidencia

    public IncidenciaConReferenciasDTO() {}

    public IncidenciaConReferenciasDTO(long id, String latitud, String longitud, String causa, String nivelIncidencia,
            String carretera, Date fechaInicio, IncidenciaCiudadPrivateDTO ciudad, IncidenciaRegionPrivateDTO region,
            IncidenciaTipoIncidenciaPrivateDTO tipoIncidencia) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.causa = causa;
        this.nivelIncidencia = nivelIncidencia;
        this.carretera = carretera;
        this.fechaInicio = fechaInicio;
        this.ciudad = ciudad;
        this.region = region;
        this.tipoIncidencia = tipoIncidencia;
    }
}
