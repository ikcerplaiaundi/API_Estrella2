package com.main.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.TipoIncidencia;
//Repositorio principal de tiposDeIncidencia
public interface TipoIncidenciaRepositorio extends JpaRepository<TipoIncidencia, Long>{

    boolean existsByNombre(String nombre);

    TipoIncidencia findByNombre(String nombre);
}
