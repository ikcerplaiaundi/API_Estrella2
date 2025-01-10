package com.main.comunicacion.privadas.DTOs;

import lombok.Data;

@Data
public class IncidenciaRegionPrivateDTO {
    private long id;
    private String nombreEs;
    private String nombreEu;

    public IncidenciaRegionPrivateDTO(long id, String nombreEs, String nombreEu) {
        this.id = id;
        this.nombreEs = nombreEs;
        this.nombreEu = nombreEu;
    }
}
