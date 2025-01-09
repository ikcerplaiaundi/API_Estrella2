package com.main.modelo.repositorios;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Provincia;

import org.hibernate.query.Query;


public interface CiudadRepositorio extends JpaRepository<Ciudad, Long> {


    

    
    boolean existsByNombre(String nombre);

    Ciudad findByNombre(String nombre);
}
