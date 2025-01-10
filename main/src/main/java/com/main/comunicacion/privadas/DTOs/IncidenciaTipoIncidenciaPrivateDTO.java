package com.main.comunicacion.privadas.DTOs;

import lombok.Data;

@Data
public class IncidenciaTipoIncidenciaPrivateDTO {
    private long id;
    private String nombre;

    public IncidenciaTipoIncidenciaPrivateDTO(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
