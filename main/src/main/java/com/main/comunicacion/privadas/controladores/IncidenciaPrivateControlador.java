package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTOCRUD;
import com.main.comunicacion.privadas.servicios.IncidenciaPrivateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.EntityNotFoundException;

/**
 * Controlador que maneja las solicitudes relacionadas con las incidencias.
 */
@RestController
@RequestMapping("")
public class IncidenciaPrivateControlador {

    private final IncidenciaPrivateService incidenciaPrivateService;

    public IncidenciaPrivateControlador(IncidenciaPrivateService incidenciaPrivateService) {
        this.incidenciaPrivateService = incidenciaPrivateService;
    }

    /**
     * Obtiene todas las incidencias registradas en el sistema.
     *
     * @return Lista de incidencias.
     */
    @Operation(
        summary = "Obtener todas las incidencias",
        description = "Obtiene una lista de todas las incidencias registradas en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencias encontradas", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidenciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron incidencias", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/incidencias")
    public ResponseEntity<List<IncidenciaPrivateDTO>> obtenerIncidencias() {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    /**
     * Obtiene incidencias filtradas por ciudad.
     *
     * @param idCiudad El ID de la ciudad para filtrar.
     * @return Lista de incidencias en la ciudad especificada.
     */
    @Operation(
        summary = "Obtener incidencias por ciudad",
        description = "Obtiene todas las incidencias filtradas por ciudad.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencias encontradas", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidenciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron incidencias para la ciudad especificada", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/filtrosIncidencias/ciudad")
    public ResponseEntity<?> filtroIncidenciaCiudad(
        @Parameter(description = "ID de la ciudad para filtrar las incidencias") @RequestParam Long idCiudad) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasCiudad(idCiudad);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    /**
     * Obtiene incidencias filtradas por provincia.
     *
     * @param idProvincia El ID de la provincia para filtrar.
     * @return Lista de incidencias en la provincia especificada.
     */
    @Operation(
        summary = "Obtener incidencias por provincia",
        description = "Obtiene todas las incidencias filtradas por provincia.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencias encontradas", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidenciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron incidencias para la provincia especificada", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/filtrosIncidencias/provincia")
    public ResponseEntity<?> filtroIncidenciaProvincia(
        @Parameter(description = "ID de la provincia para filtrar las incidencias") @RequestParam Long idProvincia) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasProvincia(idProvincia);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    /**
     * Obtiene incidencias filtradas por región.
     *
     * @param idRegion El ID de la región para filtrar.
     * @return Lista de incidencias en la región especificada.
     */
    @Operation(
        summary = "Obtener incidencias por región",
        description = "Obtiene todas las incidencias filtradas por región.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencias encontradas", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidenciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron incidencias para la región especificada", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/filtrosIncidencias/region")
    public ResponseEntity<?> filtroIncidenciaRegion(
        @Parameter(description = "ID de la región para filtrar las incidencias") @RequestParam Long idRegion) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasRegion(idRegion);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    /**
     * Obtiene incidencias filtradas por tipo de incidencia.
     *
     * @param idTipoIncidencia El ID del tipo de incidencia para filtrar.
     * @return Lista de incidencias del tipo especificado.
     */
    @Operation(
        summary = "Obtener incidencias por tipo de incidencia",
        description = "Obtiene todas las incidencias filtradas por tipo de incidencia.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencias encontradas", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = IncidenciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron incidencias para el tipo especificado", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/filtrosIncidencias/tipoIncidencia")
    public ResponseEntity<?> filtroIncidenciaTipoIncidencia(
        @Parameter(description = "ID del tipo de incidencia para filtrar") @RequestParam Long idTipoIncidencia) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasTipoIncidencia(idTipoIncidencia);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    /**
     * Crea una nueva incidencia.
     *
     * @param incidenciaPrivateDTOCRUD Los detalles de la incidencia a crear.
     * @return Mensaje de éxito o error.
     */
    @Operation(
        summary = "Crear una nueva incidencia",
        description = "Crea una incidencia con los detalles proporcionados.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencia creada con éxito",
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/crearIncidencia")
    public ResponseEntity<?> crearIncidencia(
        @RequestBody IncidenciaPrivateDTOCRUD incidenciaPrivateDTOCRUD) {
        String mensaje = incidenciaPrivateService.crearIncidencia(incidenciaPrivateDTOCRUD);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * Actualiza una incidencia existente.
     *
     * @param incidenciaPrivateDTOCRUD Los detalles de la incidencia a actualizar.
     * @return Mensaje de éxito o error.
     */
    @Operation(
        summary = "Actualizar incidencia",
        description = "Actualiza los detalles de una incidencia existente.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencia actualizada con éxito", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Incidencia no encontrada", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PutMapping("/actualizarIncidencia")
    public ResponseEntity<?> actualizarIncidencia(
        @RequestBody IncidenciaPrivateDTOCRUD incidenciaPrivateDTOCRUD) {
        try {
            String mensaje = incidenciaPrivateService.actualizarIncidencia(incidenciaPrivateDTOCRUD);
            return ResponseEntity.ok(mensaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Elimina una incidencia existente.
     *
     * @param incidenciaPrivateDTOCRUD Los detalles de la incidencia a eliminar.
     * @return Mensaje de éxito o error.
     */
    @Operation(
        summary = "Eliminar incidencia",
        description = "Elimina una incidencia existente.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Incidencia eliminada con éxito", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Incidencia no encontrada", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @DeleteMapping("/eliminarIncidencia")
    public ResponseEntity<?> eliminarIncidencia(
        @RequestBody IncidenciaPrivateDTOCRUD incidenciaPrivateDTOCRUD) {
        try {
            String mensaje = incidenciaPrivateService.eliminarIncidencia(incidenciaPrivateDTOCRUD);
            return ResponseEntity.ok(mensaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
