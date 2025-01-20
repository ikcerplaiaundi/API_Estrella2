package com.main.comunicacion.privadas.DTOs;

public class RolPrivateDTO {
    long id;
    String rol;
    
    public RolPrivateDTO(long id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
}
