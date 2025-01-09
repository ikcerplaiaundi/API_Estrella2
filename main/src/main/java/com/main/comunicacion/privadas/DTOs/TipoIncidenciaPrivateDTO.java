package com.main.comunicacion.privadas.DTOs;


import lombok.Data;


@Data
public class TipoIncidenciaPrivateDTO {
    private Integer id;
    private String nombre;

    public TipoIncidenciaPrivateDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoIncidenciaPrivateDTO() {
    }
    
    
    
}
