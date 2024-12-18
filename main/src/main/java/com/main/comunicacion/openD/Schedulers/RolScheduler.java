package com.main.comunicacion.openD.Schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.main.modelo.entidades.Rol;
import com.main.modelo.repositorios.RolRepositorio;

@Component
public class RolScheduler {
    
    @Autowired
    private RolRepositorio rolRepositorio;
    
    public void crearRoles(){
        Rol admin = new Rol("administrador");
        Rol usuario = new Rol("usuario");

        rolRepositorio.save(admin);
        rolRepositorio.save(usuario);

    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        crearRoles();
    }
}
