package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.comunicacion.privadas.servicios.RegionPrivateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/privateRegions")
public class RegionPrivateControlador {

    @Autowired
    private RegionPrivateService regionService;

    /**
     * Obtiene una región por su ID.
     *
     * @param id El ID de la región que se desea obtener.
     * @return Respuesta con la región correspondiente al ID.
     */
    @Operation(
        summary = "Obtener región por ID",
        description = "Devuelve la región correspondiente al ID proporcionado.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Región obtenida exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegionPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontró la región con el ID dado", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/{id}")
    public RegionPrivateDTO obtenerRegionPorId(@PathVariable Long id) {
        return regionService.obtenerRegionPorId(id);
    }

    /**
     * Obtiene todas las regiones.
     *
     * @return Respuesta con la lista de todas las regiones.
     */
    @Operation(
        summary = "Obtener todas las regiones",
        description = "Devuelve la lista completa de todas las regiones.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Lista de regiones obtenida exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegionPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron regiones", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping
    public List<RegionPrivateDTO> obtenerRegiones() {
        return regionService.obtenerRegiones();
    }

    /**
     * Obtiene todas las regiones sin cámaras asociadas.
     *
     * @return Respuesta con la lista de todas las regiones sin cámaras.
     */
    @Operation(
        summary = "Obtener regiones sin cámaras",
        description = "Devuelve la lista de regiones que no tienen cámaras asociadas.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Lista de regiones sin cámaras obtenida exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegionPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron regiones sin cámaras", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/soloRegiones")
    public List<RegionPrivateDTO> obtenerRegionesSinCamaras() {
        return regionService.obtenerSoloRegiones();
    }
}
