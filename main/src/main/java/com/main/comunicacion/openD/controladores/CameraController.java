package com.main.comunicacion.openD.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    @Autowired
    private CameraRepository cameraRepository;

    @Operation(summary = "Obtener todas las cámaras", description = "Recupera una lista de todas las cámaras registradas en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cámaras obtenidas correctamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron cámaras")
    })
    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        List<Camera> cameras = cameraRepository.findAll();
        return ResponseEntity.ok(cameras);
    }
}
