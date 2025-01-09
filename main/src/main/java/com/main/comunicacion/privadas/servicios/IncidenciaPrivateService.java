package com.main.comunicacion.privadas.servicios;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;
import com.main.comunicacion.privadas.DTOs.CameraDTO;
import com.main.comunicacion.mapeos.CameraPrivateMapper;

import java.util.List;
import java.util.stream.Collectors;

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

    public ResponseEntity<?> obtenerIncidencias() {
        List<Incidencia> incidencias = incidenciaRepositorio.findAll();
    
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<IncidenciaPrivateDTO> incidenciaDTOs = incidencias.stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    
            return ResponseEntity.ok(incidenciaDTOs);
        }
    }
    
}
