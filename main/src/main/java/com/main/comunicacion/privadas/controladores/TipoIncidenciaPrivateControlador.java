package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.TipoIncidenciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.TipoIncidenciaPrivateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

// Gestión de peticiones de la API interna de tipos de incidencia
@RestController
@RequestMapping("")
public class TipoIncidenciaPrivateControlador {

    private final TipoIncidenciaPrivateService tipoIncidenciaPrivateService;

    public TipoIncidenciaPrivateControlador(TipoIncidenciaPrivateService tipoIncidenciaPrivateService) {
        this.tipoIncidenciaPrivateService = tipoIncidenciaPrivateService;
    }

    /**
     * Ruta para obtener todos los tipos de incidencia.
     *
     * @return Lista de tipos de incidencia disponibles.
     */
    @Operation(
        summary = "Obtener todos los tipos de incidencia",
        description = "Este endpoint devuelve la lista completa de tipos de incidencia disponibles en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de tipos de incidencia obtenida exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoIncidenciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "No se encontraron tipos de incidencia",
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/tiposIncidencias")
    public ResponseEntity<List<TipoIncidenciaPrivateDTO>> obtenerProvincias() {
        List<TipoIncidenciaPrivateDTO> tiposIncidencia = tipoIncidenciaPrivateService.obtenerTiposIncidencias();
        if (tiposIncidencia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tiposIncidencia);
    }
}
