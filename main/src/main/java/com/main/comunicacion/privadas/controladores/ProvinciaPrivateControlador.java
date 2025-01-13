package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.ProvinciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.ProvinciaPrivateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("")
public class ProvinciaPrivateControlador {

    private final ProvinciaPrivateService provinciaPrivateService;

    public ProvinciaPrivateControlador(ProvinciaPrivateService provinciaPrivateService) {
        this.provinciaPrivateService = provinciaPrivateService;
    }

    /**
     * Endpoint para obtener todas las provincias
     * 
     * @return Lista de provincias o mensaje de error si no hay provincias
     */
    @Operation(summary = "Obtener todas las provincias", description = "Recupera una lista de todas las provincias disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de provincias recuperada exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron provincias")
    })
    @GetMapping("/api/provincias")
    public ResponseEntity<List<ProvinciaPrivateDTO>> obtenerProvincias() {
        List<ProvinciaPrivateDTO> provincias = provinciaPrivateService.obtenerProvincias();
        if (provincias.isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no hay provincias, responde con 404
        }
        return ResponseEntity.ok(provincias); // Si se encuentran provincias, responde con 200
    }
}
