package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.comunicacion.privadas.servicios.CameraPrivateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/privateCameras")
public class CameraPrivateControlador {
    
    @Autowired
    private CameraPrivateService cameraService;

    /**
     * Endpoint para obtener cámara por ID
     * 
     * @param id El ID de la cámara a obtener
     * @return El objeto CameraPrivateDTO correspondiente
     */
    @Operation(summary = "Obtener cámara por ID", description = "Recupera los detalles de una cámara específica mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cámara encontrada"),
        @ApiResponse(responseCode = "404", description = "Cámara no encontrada")
    })
    @GetMapping("/{id}")
    public CameraPrivateDTO obtenerCamaraPorId(@Parameter(description = "ID de la cámara a obtener") @PathVariable Long id) {
        return cameraService.obtenerCamaraPorId(id);
    }

    /**
     * Endpoint para obtener cámaras por región
     * 
     * @param regionId El ID de la región para filtrar las cámaras
     * @return Una lista de cámaras que pertenecen a la región especificada
     */
    @Operation(summary = "Obtener cámaras por región", description = "Recupera todas las cámaras asociadas a una región específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cámaras encontradas"),
        @ApiResponse(responseCode = "404", description = "No se encontraron cámaras para la región especificada")
    })
    @GetMapping("/region/{regionId}")
    public List<CameraPrivateDTO> obtenerCamarasPorRegion(@Parameter(description = "ID de la región para filtrar las cámaras") @PathVariable Long regionId) {
        return cameraService.obtenerCamarasPorRegion(regionId);
    }

    /**
     * Endpoint para obtener todas las cámaras
     * 
     * @return Una lista de todas las cámaras disponibles
     */
    @Operation(summary = "Obtener todas las cámaras", description = "Recupera una lista de todas las cámaras disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cámaras encontrada"),
        @ApiResponse(responseCode = "204", description = "No hay cámaras disponibles")
    })
    @GetMapping("")
    public List<CameraPrivateDTO> obtenerCamaras() {
        return cameraService.obtenerCamaras();
    }
}
