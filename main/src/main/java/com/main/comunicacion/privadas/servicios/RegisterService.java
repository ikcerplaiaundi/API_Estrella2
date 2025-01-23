package com.main.comunicacion.privadas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.modelo.entidades.Rol;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.RolRepositorio;
import com.main.modelo.repositorios.UsuarioRepositorio;


//Servicios que ofrecen todas las solicitudes de registro a la api interna
@Service
public class RegisterService {

    @Autowired
    private UsuarioRepositorio usuarioRepository;
    @Autowired
    private RolRepositorio rolRepositorio;

    public Usuario registrarUsuario(String nombre, String email, String contraseña) {

        if (usuarioRepository.existsByCorreo(email)) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (usuarioRepository.existsByNombre(nombre)) {
            throw new RuntimeException("Ya existe el nombre de usuario");
        }

        Rol adminRol = rolRepositorio.findByName("administrador")
                .orElseGet(() -> {
                    Rol nuevoRol = new Rol("administrador");
                    return rolRepositorio.save(nuevoRol);
                });

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(email);
        nuevoUsuario.setContraseña(contraseña); 
        nuevoUsuario.setRol(adminRol); 
        return usuarioRepository.save(nuevoUsuario);
    }

    public Usuario registrarUsuarioAndroid(String nombre, String email, String contraseña) {

        if (usuarioRepository.existsByCorreo(email)) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (usuarioRepository.existsByNombre(nombre)) {
            throw new RuntimeException("Ya existe el nombre de usuario");
        }

        Rol usuarioRol = rolRepositorio.findByName("usuario")
                .orElseGet(() -> {
                    Rol nuevoRol = new Rol("usuario");
                    return rolRepositorio.save(nuevoRol);
                });

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(email);
        nuevoUsuario.setContraseña(contraseña); 
        nuevoUsuario.setRol(usuarioRol); 
        return usuarioRepository.save(nuevoUsuario);
    }
}


