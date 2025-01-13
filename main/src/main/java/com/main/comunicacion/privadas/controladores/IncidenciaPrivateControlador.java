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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaPrivateControlador {

    @Autowired
    IncidenciaPrivateService incidenciaPrivateService;

    /**
     * Endpoint para obtener todas las incidencias con referencias completas
     * 
     * @return Lista de incidencias
     */
    @Operation(summary = "Obtener todas las incidencias", description = "Recupera una lista de todas las incidencias con sus referencias completas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de incidencias encontrada"),
        @ApiResponse(responseCode = "204", description = "No se encontraron incidencias")
    })
    @GetMapping
    public ResponseEntity<List<IncidenciaConReferenciasDTO>> obtenerIncidencias() {
        List<IncidenciaConReferenciasDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasConReferencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.noContent().build(); // Responde con 204 si no hay incidencias
        } else {
            return ResponseEntity.ok(incidencias); // Retorna la lista de incidencias
        }
    }

    /**
     * Endpoint para obtener una incidencia específica por ID con referencias completas
     * 
     * @param id ID de la incidencia
     * @return Incidencia con las referencias completas
     */
    @Operation(summary = "Obtener incidencia por ID", description = "Recupera una incidencia específica por su ID con todas las referencias.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia encontrada"),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaConReferenciasDTO> obtenerIncidenciaPorId(@PathVariable Long id) {
        IncidenciaConReferenciasDTO incidencia = incidenciaPrivateService.obtenerIncidenciaConReferenciasPorId(id);
        if (incidencia == null) {
            return ResponseEntity.notFound().build(); // No se encontró la incidencia
        } else {
            return ResponseEntity.ok(incidencia); // Retorna la incidencia con referencias
        }
    }

    /**
     * Endpoint para crear una incidencia
     * 
     * @param incidenciaDTO Datos de la incidencia a crear
     * @return Mensaje de confirmación
     */
    @Operation(summary = "Crear una incidencia", description = "Crea una nueva incidencia y devuelve el mensaje de confirmación con el ID generado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Incidencia creada"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<String> crearIncidencia(@RequestBody IncidenciaActualizarDTO incidenciaDTO) {
        String mensaje = incidenciaPrivateService.crearIncidencia(incidenciaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    /**
     * Endpoint para actualizar una incidencia
     * 
     * @param incidenciaDTO Datos de la incidencia a actualizar
     * @return Mensaje de confirmación
     */
    @Operation(summary = "Actualizar incidencia", description = "Actualiza una incidencia existente y devuelve el mensaje de confirmación.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia actualizada"),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @PutMapping
    public ResponseEntity<String> actualizarIncidencia(@RequestBody IncidenciaActualizarDTO incidenciaDTO) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.actualizarIncidencia(incidenciaDTO));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint para eliminar una incidencia
     * 
     * @param id ID de la incidencia a eliminar
     * @return Mensaje de confirmación
     */
    @Operation(summary = "Eliminar incidencia", description = "Elimina una incidencia específica por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Incidencia eliminada"),
        @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incidenciaPrivateService.eliminarIncidencia(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
