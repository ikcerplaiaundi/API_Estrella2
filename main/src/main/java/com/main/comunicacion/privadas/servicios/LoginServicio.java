package com.main.comunicacion.privadas.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.UsuarioRepositorio;

import java.util.Optional;

@Service
public class LoginServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario login(String nombre, String contraseña) {
        Optional<Usuario> usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario.isPresent() && usuario.get().getContraseña().equals(contraseña)) {
            return usuario.get();
        }
        return null;
    }
}

