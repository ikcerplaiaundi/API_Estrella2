package com.main.comunicacion.privadas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.CameraDTO;
import com.main.comunicacion.privadas.servicios.CameraPrivateService;

import java.util.List;

@RestController
@RequestMapping("/api/cameras")
public class CameraPrivateControlador {

    private final CameraPrivateService cameraService;

    @Autowired
    public CameraPrivateControlador(CameraPrivateService cameraService) {
        this.cameraService = cameraService;
    }

    // Endpoint para obtener cámara por ID
    @GetMapping("/privateCameras/{id}")
    public CameraDTO obtenerCamaraPorId(@PathVariable Long id) {
        return cameraService.obtenerCamaraPorId(id);
    }

    // Endpoint para obtener cámaras por región
    /* 
    @GetMapping("/privateCameras/region/{regionId}")
    public List<CameraDTO> obtenerCamarasPorRegion(@PathVariable Long regionId) {
        return cameraService.obtenerCamarasPorRegion(regionId);
    }
*/
    // Endpoint para obtener todas las cámaras
    @GetMapping("/privateCameras")
    public List<CameraDTO> obtenerCamaras() {
        return cameraService.obtenerCamaras();
    }
}