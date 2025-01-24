package com.main.comunicacion.privadas.servicios;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.main.modelo.entidades.Rol;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.RolRepositorio;
import com.main.modelo.repositorios.UsuarioRepositorio;

@Service
public class RegisterService {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private RolRepositorio rolRepositorio;

    public Usuario registrarUsuario(String nombre, String email, String contraseña) {
        validarUsuarioUnico(nombre, email);
        Rol adminRol = obtenerOCrearRol("administrador");
        
        Usuario nuevoUsuario = crearNuevoUsuario(nombre, email, contraseña, adminRol);
        return usuarioRepository.save(nuevoUsuario);
    }

    public Usuario registrarUsuarioAndroid(String nombre, String email, String contraseña) {
        validarUsuarioUnico(nombre, email);
        Rol usuarioRol = obtenerOCrearRol("usuario");
        
        Usuario nuevoUsuario = crearNuevoUsuario(nombre, email, contraseña, usuarioRol);
        return usuarioRepository.save(nuevoUsuario);
    }

    private void validarUsuarioUnico(String nombre, String email) {
        if (usuarioRepository.existsByCorreo(email)) {
            throw new RuntimeException("El correo ya está registrado");
        }
        if (usuarioRepository.existsByNombre(nombre)) {
            throw new RuntimeException("Ya existe el nombre de usuario");
        }
    }

    private Rol obtenerOCrearRol(String nombreRol) {
        return rolRepositorio.findByName(nombreRol)
                .orElseGet(() -> rolRepositorio.save(new Rol(nombreRol)));
    }

    private Usuario crearNuevoUsuario(String nombre, String email, String contraseña, Rol rol) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(email);
        nuevoUsuario.setContraseña(contraseña);
        nuevoUsuario.setRol(rol);
        return nuevoUsuario;
    }

    
}
