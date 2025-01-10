package com.main.comunicacion.privadas.DTOs;

import lombok.Data;

@Data
public class IncidenciaCiudadPrivateDTO {
    private long id;
    private String nombre;

    public IncidenciaCiudadPrivateDTO(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
