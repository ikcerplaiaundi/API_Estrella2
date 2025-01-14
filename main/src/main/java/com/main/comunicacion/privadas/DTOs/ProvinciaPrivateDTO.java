package com.main.comunicacion.privadas.DTOs;


import lombok.Data;

//DTO de provincia de la api interna
@Data
public class ProvinciaPrivateDTO {
    private long id;
    private String nombre;
    private String latitud;
    private String longitud;
    
    public ProvinciaPrivateDTO(long id, String nombre, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public ProvinciaPrivateDTO() {
    }
}
