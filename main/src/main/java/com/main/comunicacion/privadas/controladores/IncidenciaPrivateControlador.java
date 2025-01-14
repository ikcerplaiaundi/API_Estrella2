package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.IncidenciaPrivateService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class IncidenciaPrivateControlador {

    private final IncidenciaPrivateService incidenciaPrivateService;

    public IncidenciaPrivateControlador(IncidenciaPrivateService incidenciaPrivateService) {
        this.incidenciaPrivateService = incidenciaPrivateService;
    }

    @GetMapping("/incidencias")
    public ResponseEntity<List<IncidenciaPrivateDTO>> obtenerIncidencias() {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    @PostMapping("/filtrosIncidencias")
    public ResponseEntity<?> filtroIncidenciaCiudad(@RequestParam Long idCiudad) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasCiudad(idCiudad);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    // @PostMapping("/filtrosIncidencias/{provincia}")

    // @PostMapping("/filtrosIncidencias/{region}")

    @PostMapping("/crearIncidencia")
    public ResponseEntity<?> crearIncidencia(@RequestBody IncidenciaPrivateDTO incidenciaDTO) {

        String mensaje = incidenciaPrivateService.crearIncidencia(incidenciaDTO);
        return ResponseEntity.ok(mensaje);

    }

    @PutMapping("/actualizarIncidencia")
    public ResponseEntity<?> actualizarIncidencia(@RequestBody IncidenciaPrivateDTO incidenciaDTO) {
        try {
            String mensaje = incidenciaPrivateService.actualizarIncidencia(incidenciaDTO);
            return ResponseEntity.ok(mensaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminarIncidencia")
    public ResponseEntity<?> eliminarIncidencia(@RequestBody IncidenciaPrivateDTO incidenciaDTO) {
        try {
            String mensaje = incidenciaPrivateService.eliminarIncidencia(incidenciaDTO);
            return ResponseEntity.ok(mensaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}