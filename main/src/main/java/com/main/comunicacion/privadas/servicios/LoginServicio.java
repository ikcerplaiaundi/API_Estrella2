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

    //Servicio con el cual logueremos a un usuario
    public Usuario login(String nombre, String contraseña) {
        Optional<Usuario> usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario.isPresent() && usuario.get().getContraseña().equals(hashearContraseña(contraseña))) {
            return usuario.get();
        }
        return null;
    }

    private String hashearContraseña(String contraseña) {
        return DigestUtils.md5DigestAsHex(contraseña.getBytes(StandardCharsets.UTF_8));
    }
}

