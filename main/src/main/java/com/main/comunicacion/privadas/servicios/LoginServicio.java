package com.main.comunicacion.privadas.servicios;


import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.UsuarioRepositorio;

//Servicios que ofrecen todas las solicitudes de login a la api interna
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

