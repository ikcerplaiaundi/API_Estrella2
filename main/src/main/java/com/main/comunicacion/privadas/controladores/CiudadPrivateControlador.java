package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.CiudadPrivateDTO;
import com.main.comunicacion.privadas.servicios.CiudadPrivateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador que maneja las solicitudes relacionadas con las ciudades.
 */
@RestController
@RequestMapping("")
public class CiudadPrivateControlador {

    private final CiudadPrivateService ciudadPrivateService;

    public CiudadPrivateControlador(CiudadPrivateService ciudadPrivateService) {
        this.ciudadPrivateService = ciudadPrivateService;
    }

    /**
     * Obtiene todas las ciudades disponibles en el sistema.
     *
     * @return Lista de todas las ciudades.
     */
    @Operation(
        summary = "Obtener todas las ciudades",
        description = "Obtiene una lista de todas las ciudades registradas en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Ciudades encontradas", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CiudadPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron ciudades", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/ciudades")
    public ResponseEntity<List<CiudadPrivateDTO>> obtenerCiudades() {
        List<CiudadPrivateDTO> ciudades = ciudadPrivateService.obtenerCiudades();
        if (ciudades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ciudades);
    }
}
