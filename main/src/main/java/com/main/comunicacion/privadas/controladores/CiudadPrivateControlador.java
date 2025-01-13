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
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("")
public class CiudadPrivateControlador {

    private final CiudadPrivateService ciudadPrivateService;

    public CiudadPrivateControlador(CiudadPrivateService ciudadPrivateService) {
        this.ciudadPrivateService = ciudadPrivateService;
    }

    /**
     * Endpoint para obtener todas las ciudades
     * 
     * @return Lista de todas las ciudades disponibles
     */
    @Operation(summary = "Obtener todas las ciudades", description = "Recupera una lista de todas las ciudades disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de ciudades encontrada"),
        @ApiResponse(responseCode = "404", description = "No se encontraron ciudades")
    })
    @GetMapping("/api/ciudades")
    public ResponseEntity<List<CiudadPrivateDTO>> obtenerCiudades() {
        List<CiudadPrivateDTO> ciudades = ciudadPrivateService.obtenerCiudades();
        if (ciudades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ciudades);
    }
}
