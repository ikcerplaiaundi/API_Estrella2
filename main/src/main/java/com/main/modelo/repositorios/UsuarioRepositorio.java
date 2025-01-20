package com.main.modelo.repositorios;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Usuario;
//Repositorio principal de usuarios
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findById(int id);
    boolean existsByCorreo(String correo);
    boolean existsByNombre(String nombre);
    
}
