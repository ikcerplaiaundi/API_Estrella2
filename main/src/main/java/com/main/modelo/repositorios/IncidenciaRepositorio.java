package com.main.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Incidencia;


public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

    List<Incidencia> findByCiudad_Id(Long idCiudad);

    
}
