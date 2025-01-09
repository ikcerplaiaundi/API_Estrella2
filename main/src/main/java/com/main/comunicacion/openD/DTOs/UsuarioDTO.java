package com.main.comunicacion.openD.DTOs;

public class UsuarioDTO {
    private long id;
    private String nombre;
    private String correo;
    private String contraseña;
    private String rol;

    public UsuarioDTO(long id, String nombre, String correo,String contraseña, String rol) {
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

    public String getRol() {
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

    public void setRol(String rol) {
        this.rol = rol;
    }

    
}
