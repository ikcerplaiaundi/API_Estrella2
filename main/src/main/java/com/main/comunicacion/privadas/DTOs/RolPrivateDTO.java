package com.main.comunicacion.privadas.DTOs;

public class RolPrivateDTO {
    long id;
    String nombre;
    
    public RolPrivateDTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
