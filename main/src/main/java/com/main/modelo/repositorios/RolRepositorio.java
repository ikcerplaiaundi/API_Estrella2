package com.main.modelo.repositorios;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Rol;
public interface RolRepositorio extends JpaRepository<Rol, Long> {
    
    boolean existsByName(String name);
    Optional<Rol> findByName(String name);

}
