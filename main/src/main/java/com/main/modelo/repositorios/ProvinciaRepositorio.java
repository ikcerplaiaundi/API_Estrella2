package com.main.modelo.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Provincia;

public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long> {
    
}
