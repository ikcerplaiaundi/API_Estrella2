package com.main.comunicacion.privadas.DTOs;


import lombok.Data;


@Data
public class TipoIncidenciaPrivateDTO {
    private long id;
    private String nombre;

    public TipoIncidenciaPrivateDTO(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoIncidenciaPrivateDTO() {
    }
    
    
    
}
