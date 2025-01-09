package com.main.modelo.repositorios;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Usuario;
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByNombre(String nombre);
    boolean existsByCorreo(String correo);
    boolean existsByNombre(String nombre);
}
