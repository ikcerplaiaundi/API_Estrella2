package com.main.comunicacion.openD.Schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.servicios.CameraService;
import com.main.modelo.entidades.Rol;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.UsuarioRepositorio;

@Component
public class UsuarioScheduler {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void crearUsuarios(){
        
        Rol admin = new Rol("administrador");
        Rol user = new Rol("usuario");

        Usuario admin_mijael = new Usuario("mijael", "admin", "admin_mijael@estrella2.com", admin);
        Usuario admin_irene = new Usuario("irene", "admin", "admin_irene@estrella2.com", admin);
        Usuario admin_dari = new Usuario("dari", "admin", "admin_dari@estrella2.com", admin);

        Usuario usuario = new Usuario("user", "user", "user@estrella2.com", user);

        usuarioRepositorio.save(admin_mijael);
        usuarioRepositorio.save(admin_irene);
        usuarioRepositorio.save(admin_dari);
        usuarioRepositorio.save(usuario);
    }
}
