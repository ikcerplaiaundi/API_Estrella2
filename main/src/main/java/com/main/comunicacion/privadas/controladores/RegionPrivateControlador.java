package com.main.comunicacion.privadas.controladores;

import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.comunicacion.privadas.servicios.RegionPrivateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/privateRegions")
public class RegionPrivateControlador {
    
    @Autowired
    private RegionPrivateService regionService;

    /**
     * Endpoint para obtener una región por su ID
     * 
     * @param id El ID de la región
     * @return La región correspondiente al ID
     */
    @Operation(summary = "Obtener región por ID", description = "Recupera la región asociada al ID proporcionado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Región recuperada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Región no encontrada")
    })
    @GetMapping("/{id}")
    public RegionPrivateDTO obtenerRegionPorId(@PathVariable Long id) {
        return regionService.obtenerRegionPorId(id);
    }

    /**
     * Endpoint para obtener todas las regiones
     * 
     * @return Lista de todas las regiones
     */
    @Operation(summary = "Obtener todas las regiones", description = "Recupera una lista de todas las regiones disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de regiones recuperada exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron regiones")
    })
    @GetMapping
    public List<RegionPrivateDTO> obtenerRegiones() {
        return regionService.obtenerRegiones();
    }
}
