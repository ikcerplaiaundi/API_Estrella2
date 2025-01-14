package com.main.comunicacion.privadas.DTOs;


import java.util.List;

import com.main.modelo.entidades.Ciudad;

import lombok.Data;


@Data
public class ProvinciaPrivateDTO {
    private long id;
    private String nombre;
    private String latitud;
    private String longitud;
    private List<Ciudad> ciudad;
    
    public ProvinciaPrivateDTO(long id, String nombre, String latitud, String longitud, List<Ciudad> ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
    }

    public ProvinciaPrivateDTO() {
    }
}
