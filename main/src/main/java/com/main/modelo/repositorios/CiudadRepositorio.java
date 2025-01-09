package com.main.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Ciudad;


public interface CiudadRepositorio extends JpaRepository<Ciudad, Long> {


    

    
    boolean existsByNombre(String nombre);

    Ciudad findByNombre(String nombre);
}
