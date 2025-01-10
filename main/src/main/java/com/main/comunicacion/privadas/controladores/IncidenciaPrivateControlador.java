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
        // Lógica para obtener todas las incidencias con referencias
        List<IncidenciaConReferenciasDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasConReferencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.noContent().build(); // Responde con 204 si no hay incidencias
        } else {
            return ResponseEntity.ok(incidencias); // Retorna la lista de incidencias
        }
    }
    /*
     * Ejemplo de petición:
     * GET http://localhost:8080/incidencias
     * Respuesta exitosa:
     * [
     * {
     * "id": 1,
     * "descripcion": "Incidencia en el sistema",
     * "ciudad": "Madrid",
     * "tipoIncidencia": "Error de software",
     * "region": "Europa"
     * }
     * ]
     */

    // Obtener una incidencia específica por ID con referencias completas
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaConReferenciasDTO> obtenerIncidenciaPorId(@PathVariable Long id) {
        IncidenciaConReferenciasDTO incidencia = incidenciaPrivateService.obtenerIncidenciaConReferenciasPorId(id);

        if (incidencia == null) {
            return ResponseEntity.notFound().build(); // No se encontró la incidencia
        } else {
            return ResponseEntity.ok(incidencia); // Retorna la incidencia con referencias
        }
    }
    /*
     * Ejemplo de petición:
     * GET http://localhost:8080/incidencias/1
     * Respuesta exitosa:
     * {
     * "id": 1,
     * "descripcion": "Incidencia en el sistema",
     * "ciudad": "Madrid",
     * "tipoIncidencia": "Error de software",
     * "region": "Europa"
     * }
     */

    // Crear una incidencia
    @PostMapping
    public ResponseEntity<String> crearIncidencia(@RequestBody IncidenciaActualizarDTO incidenciaDTO) {
        String mensaje = incidenciaPrivateService.crearIncidencia(incidenciaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }
    /*
     * Ejemplo de petición:
     * POST http://localhost:8080/incidencias
     * Body (JSON):
     * {
     * "descripcion": "Nueva incidencia",
     * "idCiudad": 2,
     * "idTipoIncidencia": 3,
     * "idRegion": 1
     * }
     * Respuesta exitosa:
     * "Incidencia creada con ID: 5"
     */

    // Actualizar una incidencia
    @PutMapping
    public ResponseEntity<String> actualizarIncidencia(@RequestBody IncidenciaActualizarDTO incidenciaDTO) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.actualizarIncidencia(incidenciaDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    /*
     * Ejemplo de petición:
     * PUT http://localhost:8080/incidencias
     * Body (JSON):
     * {
     * "id": 1,
     * "descripcion": "Incidencia actualizada",
     * "idCiudad": 2,
     * "idTipoIncidencia": 3,
     * "idRegion": 1
     * }
     * Respuesta exitosa:
     * "Incidencia actualizada con ID: 1"
     */

    // Eliminar una incidencia
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.eliminarIncidencia(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
/*
 * Ejemplo de petición:
 * GET http://localhost:8080/incidencias/delete/1
 * Respuesta exitosa:
 * "Incidencia eliminada con éxito."
 */
}