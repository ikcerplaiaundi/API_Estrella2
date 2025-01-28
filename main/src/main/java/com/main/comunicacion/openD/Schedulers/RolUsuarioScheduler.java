package com.main.comunicacion.openD.Schedulers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.main.modelo.entidades.Rol;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.RolRepositorio;
import com.main.modelo.repositorios.UsuarioRepositorio;

import jakarta.annotation.PostConstruct;
//Petinciones sincronizadas con RolUsuario
@Component
public class RolUsuarioScheduler {

    @Autowired
    private RolRepositorio rolRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    @PostConstruct
    public void crearRolesUsuarios() {
        List<String> roles = List.of("administrador", "usuario");
        roles.forEach(rol -> {
            if (!rolRepositorio.existsByName(rol)) {
                rolRepositorio.save(new Rol(rol));
            }
        });
    
        Rol adminRol = rolRepositorio.findByName("administrador").get();
        Rol usuarioRol = rolRepositorio.findByName("usuario").get();
    
        List<Usuario> usuarios = List.of(
            new Usuario("mijael", hashearContraseña("admin"), "admin_mijael@estrella2.com", adminRol),
            new Usuario("irene", hashearContraseña("admin"), "admin_irene@estrella2.com", adminRol),
            new Usuario("dari", hashearContraseña("admin"), "admin_dari@estrella2.com", adminRol),
            new Usuario("user1", hashearContraseña("usuario"), "user1@estrella2.com", usuarioRol),
            new Usuario("user2", hashearContraseña("usuario"), "user2@estrella2.com", usuarioRol),
            new Usuario("user3", hashearContraseña("usuario"), "user3@estrella2.com", usuarioRol)
        );
    
        usuarios.forEach(usuario -> {
            if (!usuarioRepositorio.existsByCorreo(usuario.getCorreo())) {
                usuarioRepositorio.save(usuario);
            }
        });


        
    }

    private String hashearContraseña(String contraseña) {
        return DigestUtils.md5DigestAsHex(contraseña.getBytes(StandardCharsets.UTF_8));
    }
}
