package com.main.modelo.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Rol;
public interface RolRepositorio extends JpaRepository<Rol, Long> {
}
