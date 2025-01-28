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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Controlador que maneja las solicitudes relacionadas con las cámaras.
 */
@RestController
@RequestMapping("/privateCameras")
public class CameraPrivateControlador {

    @Autowired
    private CameraPrivateService cameraService;

    /**
     * Obtiene los detalles de una cámara por su ID.
     *
     * @param id El ID de la cámara.
     * @return El DTO de la cámara correspondiente.
     */
    @Operation(
        summary = "Obtener cámara por ID",
        description = "Obtiene los detalles de una cámara basada en el ID proporcionado.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Cámara encontrada", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CameraPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Cámara no encontrada", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/{id}")
    public CameraPrivateDTO obtenerCamaraPorId(
        @Parameter(description = "ID de la cámara") @PathVariable Long id
    ) {
        return cameraService.obtenerCamaraPorId(id);
    }

    /**
     * Obtiene una lista de cámaras asociadas a una región específica.
     *
     * @param regionId El ID de la región.
     * @return Lista de cámaras en la región.
     */
    @Operation(
        summary = "Obtener cámaras por región",
        description = "Obtiene todas las cámaras asociadas a una región especificada por su ID.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Cámaras encontradas",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CameraPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "Región o cámaras no encontradas", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/region/{regionId}")
    public List<CameraPrivateDTO> obtenerCamarasPorRegion(
        @Parameter(description = "ID de la región") @PathVariable Long regionId
    ) {
        return cameraService.obtenerCamarasPorRegion(regionId);
    }

    /**
     * Obtiene todas las cámaras disponibles en la base de datos.
     *
     * @return Lista de todas las cámaras.
     */
    @Operation(
        summary = "Obtener todas las cámaras",
        description = "Obtiene una lista de todas las cámaras registradas en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Cámaras encontradas", 
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CameraPrivateDTO.class))
            )
        }
    )
    @GetMapping("")
    public List<CameraPrivateDTO> obtenerCamaras() {
        return cameraService.obtenerCamaras();
    }
}
