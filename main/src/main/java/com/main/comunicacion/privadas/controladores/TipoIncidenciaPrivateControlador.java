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
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("")
public class TipoIncidenciaPrivateControlador {

    private final TipoIncidenciaPrivateService tipoIncidenciaPrivateService;

    public TipoIncidenciaPrivateControlador(TipoIncidenciaPrivateService tipoIncidenciaPrivateService) {
        this.tipoIncidenciaPrivateService = tipoIncidenciaPrivateService;
    }

    /**
     * Endpoint para obtener todos los tipos de incidencias
     * 
     * @return Lista de tipos de incidencias
     */
    @Operation(summary = "Obtener todos los tipos de incidencias", description = "Recupera una lista de todos los tipos de incidencias disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tipos de incidencias obtenidos exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron tipos de incidencias")
    })
    @GetMapping("/api/tiposIncidencias")
    public ResponseEntity<List<TipoIncidenciaPrivateDTO>> obtenerProvincias() {
        List<TipoIncidenciaPrivateDTO> tiposIncidencia = tipoIncidenciaPrivateService.obtenerTiposIncidencias();
        if (tiposIncidencia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tiposIncidencia);
    }
}
