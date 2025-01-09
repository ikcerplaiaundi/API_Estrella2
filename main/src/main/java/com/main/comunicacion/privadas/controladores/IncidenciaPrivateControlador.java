package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.CameraDTO;
import com.main.comunicacion.privadas.servicios.CameraPrivateService;

@RestController
@RequestMapping("/api/cameras")
public class IncidenciaPrivateControlador {

    
    private final IncidenciaPrivateService incidenciaPrivateService;

    @Autowired
    public IncidenciaPrivateControlador(IncidenciaPrivateService incidenciaPrivateService) {
        this.incidenciaPrivateService = incidenciaPrivateService;
    }

    // Este endpoint devuelve el listado de cámaras con sus detalles en formato JSON
    @GetMapping("/api/privateCameras")
    public List<CameraDTO> obtenerCamaras() {
        return incidenciaPrivateService.obtenerCamaras();
    }
}