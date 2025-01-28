package com.main.comunicacion.privadas.DTOs;

import com.main.modelo.entidades.Rol;

//DTO de usuario de la api interna
public class UsuarioDTO {
    private long id;
    private String nombre;
    private String correo;
    private String contraseña;
    private RolPrivateDTO rol;

    public UsuarioDTO(long id, String nombre, String correo,String contraseña, RolPrivateDTO rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public RolPrivateDTO getRol() {
        return rol;
    }
    
    public String getContraseña() {
        return contraseña;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRol(RolPrivateDTO rol) {
        this.rol = rol;
    }

    
}
