package com.main.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.modelo.entidades.Incidencia;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

    List<Incidencia> findByCiudad_Id(Long idCiudad);

    @Query("SELECT i FROM Incidencia i " +
            "JOIN FETCH i.ciudad c " +
            "JOIN FETCH c.provincia p " +
            "WHERE p.id = :idProvincia")
    List<Incidencia> findByProvinciaId(@Param("idProvincia") Long idProvincia);

    List<Incidencia> findByRegion_Id(Long idRegion);

    List<Incidencia> findByTipoIncidencia_Id(Long idTipoIncidencia);
}
