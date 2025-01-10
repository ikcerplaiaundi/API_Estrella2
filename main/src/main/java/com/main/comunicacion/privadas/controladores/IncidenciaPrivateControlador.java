package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.comunicacion.privadas.DTOs.IncidenciaActualizarDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaConReferenciasDTO;
import com.main.comunicacion.privadas.servicios.IncidenciaPrivateService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaPrivateControlador {

    @Autowired
    IncidenciaPrivateService incidenciaPrivateService;

    // Obtener todas las incidencias con referencias completas
    @GetMapping
    public ResponseEntity<List<IncidenciaConReferenciasDTO>> obtenerIncidencias() {
        List<IncidenciaConReferenciasDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasConReferencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(incidencias);
        }
    }
    // Obtener una incidencia específica por ID con referencias completas
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaConReferenciasDTO> obtenerIncidenciaPorId(@PathVariable Long id) {
        // Llamar al servicio para obtener la incidencia por ID
        IncidenciaConReferenciasDTO incidencia = incidenciaPrivateService.obtenerIncidenciaConReferenciasPorId(id);

        // Verificar si la incidencia fue encontrada
        if (incidencia == null) {
            return ResponseEntity.notFound().build(); // No se encontró la incidencia
        } else {
            return ResponseEntity.ok(incidencia); // Retorna la incidencia con referencias
        }
    }

    // Crear una incidencia
    @PostMapping
    public ResponseEntity<String> crearIncidencia(@RequestBody IncidenciaActualizarDTO incidenciaDTO) {
        String mensaje = incidenciaPrivateService.crearIncidencia(incidenciaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    // Actualizar una incidencia
    @PutMapping
    public ResponseEntity<String> actualizarIncidencia(@RequestBody IncidenciaActualizarDTO incidenciaDTO) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.actualizarIncidencia(incidenciaDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Eliminar una incidencia
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.eliminarIncidencia(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
