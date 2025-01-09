package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.IncidenciaPrivateService;

@RestController
@RequestMapping("")
public class IncidenciaPrivateControlador {

    private final IncidenciaPrivateService incidenciaPrivateService;

    
    public IncidenciaPrivateControlador(IncidenciaPrivateService incidenciaPrivateService) {
        this.incidenciaPrivateService = incidenciaPrivateService;
    }

    @GetMapping("/api/incidencias")
    public ResponseEntity<List<IncidenciaPrivateDTO>> obtenerIncidencias() {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }
}
