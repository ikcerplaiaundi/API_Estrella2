package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.IncidenciaPrivateService;
import com.main.modelo.entidades.Incidencia;

import jakarta.persistence.EntityNotFoundException;
@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaPrivateControlador {
    @Autowired
    IncidenciaPrivateService incidenciaPrivateService;
    @GetMapping
    public ResponseEntity<List<IncidenciaPrivateDTO>> obtenerIncidencias() {
                List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidencias();
        return incidencias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(incidencias);
    }

    @PostMapping
    public ResponseEntity<String> crearIncidencia(@RequestBody IncidenciaPrivateDTO incidenciaDTO) {
        String mensaje = incidenciaPrivateService.crearIncidencia(incidenciaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @PutMapping
    public ResponseEntity<String> actualizarIncidencia(@RequestBody IncidenciaPrivateDTO incidenciaDTO) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.actualizarIncidencia(incidenciaDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.eliminarIncidencia(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
