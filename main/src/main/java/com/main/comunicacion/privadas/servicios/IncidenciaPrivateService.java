package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.IncidenciaPrivateMapper;
import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.repositorios.IncidenciaRepositorio;

@Service
public class IncidenciaPrivateService {

    private final IncidenciaRepositorio incidenciaRepositorio;

    public IncidenciaPrivateService(IncidenciaRepositorio incidenciaRepositorio) {
        this.incidenciaRepositorio = incidenciaRepositorio;
    }

    public List<IncidenciaPrivateDTO> obtenerIncidencias() {
        List<Incidencia> incidencias = incidenciaRepositorio.findAll();
        return incidencias.stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    }
}
