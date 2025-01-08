package com.main.comunicacion.privadas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    // Este endpoint devuelve el listado de cámaras con sus detalles en formato JSON
    @GetMapping("/api/privateCameras")
    public List<CameraDTO> obtenerCamaras() {
        return cameraService.obtenerCamaras();
    }
}