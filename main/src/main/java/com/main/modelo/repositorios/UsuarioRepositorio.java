package com.main.modelo.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Usuario;
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    boolean existsByCorreo(String correo);
}
