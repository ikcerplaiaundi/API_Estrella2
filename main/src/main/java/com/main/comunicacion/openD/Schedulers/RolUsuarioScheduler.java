package com.main.comunicacion.openD.Schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.main.modelo.entidades.Rol;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.RolRepositorio;
import com.main.modelo.repositorios.UsuarioRepositorio;

import jakarta.annotation.PostConstruct;

import org.springframework.transaction.annotation.Transactional;
@Component
public class RolUsuarioScheduler {

    @Autowired
    private RolRepositorio rolRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    @PostConstruct
    public void crearRolesUsuarios(){
        System.out.println("");
        Rol admin = new Rol("administrador");
        Rol usuario = new Rol("usuario");

        rolRepositorio.save(admin);
        rolRepositorio.save(usuario);

        Usuario admin_mijael = new Usuario("mijael", "admin", "admin_mijael@estrella2.com", admin);
        Usuario admin_irene = new Usuario("irene", "admin", "admin_irene@estrella2.com", admin);
        Usuario admin_dari = new Usuario("dari", "admin", "admin_dari@estrella2.com", admin);
        Usuario user = new Usuario("user", "user", "user@estrella2.com", usuario);

        usuarioRepositorio.save(admin_mijael);
        usuarioRepositorio.save(admin_irene);
        usuarioRepositorio.save(admin_dari);
        usuarioRepositorio.save(user);
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            crearRolesUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erorrrrrr funcion -----------------------------------------------");
        }
    }
}
