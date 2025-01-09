package com.main.comunicacion.privadas.DTOs;


import lombok.Data;


@Data
public class ProvinciaPrivateDTO {
    private Integer id;
    private String nombre;
    private String latitud;
    private String longitud;
    
    public ProvinciaPrivateDTO(Integer id, String nombre, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public ProvinciaPrivateDTO() {
    }
}
