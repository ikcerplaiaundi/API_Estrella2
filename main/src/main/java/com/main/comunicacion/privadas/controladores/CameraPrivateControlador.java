package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.comunicacion.privadas.servicios.CameraPrivateService;

//Gestion de peticiones de la api interna de camaras
@RestController
@RequestMapping("/privateCameras")
public class CameraPrivateControlador {
    @Autowired
    private CameraPrivateService cameraService;

    // Endpoint para obtener cámara por ID
    @GetMapping("/{id}")
    public CameraPrivateDTO obtenerCamaraPorId(@PathVariable Long id) {
        return cameraService.obtenerCamaraPorId(id);
    }

    // Endpoint para obtener cámaras por región
    @GetMapping("/region/{regionId}")
    public List<CameraPrivateDTO> obtenerCamarasPorRegion(@PathVariable Long regionId) {
        return cameraService.obtenerCamarasPorRegion(regionId);
    }

    // Endpoint para obtener todas las cámaras
    @GetMapping("")
    public List<CameraPrivateDTO> obtenerCamaras() {
        return cameraService.obtenerCamaras();
    }
}

