package com.main.comunicacion.privadas.DTOs;


import lombok.Data;


@Data
public class CiudadPrivateDTO {
    private long id;
    private String nombre;
    private String latitud;
    private String longitud;
    
    public CiudadPrivateDTO(long id, String nombre, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public CiudadPrivateDTO() {
    }
}
