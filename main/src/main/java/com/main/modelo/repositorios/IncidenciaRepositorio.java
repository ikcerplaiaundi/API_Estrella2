package com.main.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Incidencia;
import com.main.modelo.entidades.TipoIncidencia;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

    void save(TipoIncidencia tipoIncidencia);

}
