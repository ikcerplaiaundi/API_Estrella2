package com.main.comunicacion.privadas.DTOs;

//DTO de rol de la api interna
public class RolPrivateDTO {
    long id;
    String nombre;
    
    public RolPrivateDTO(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
