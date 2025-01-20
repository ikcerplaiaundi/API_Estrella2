package com.main.modelo.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.entidades.Provincia;

//Repositorio principal de provincias
public interface ProvinciaRepositorio extends JpaRepository<Provincia, Long> {
    
    boolean existsByNombre(String nombre);

    Provincia findByNombre(String nombre);
    


}
