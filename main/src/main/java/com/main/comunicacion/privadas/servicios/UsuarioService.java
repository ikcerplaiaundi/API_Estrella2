package com.main.comunicacion.privadas.servicios;

import com.main.modelo.entidades.Rol;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.RolRepositorio;
import com.main.modelo.repositorios.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private RolRepositorio rolRepository;

    public Usuario actualizarUsuario(int id, String nombre, String correo, String contraseña, int rolId) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
    
        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + rolId));
    
        if (nombre != null && !nombre.isBlank()) {
            usuario.setNombre(nombre);
        }
    
        if (correo != null && !correo.isBlank()) {
            usuario.setCorreo(correo);
        }
    
        if (contraseña != null && !contraseña.isBlank()) {
            usuario.setContraseña(contraseña);
        }
    
        usuario.setRol(rol);
    
        return usuarioRepository.save(usuario);
    }
    
    public void eliminarUsuario(long id){
        usuarioRepository.deleteById(id);
    }
    
}

