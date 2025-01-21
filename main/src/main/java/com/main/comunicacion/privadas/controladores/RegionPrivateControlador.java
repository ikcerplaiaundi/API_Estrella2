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

//Gestion de peticiones de la api interna de regiones
@RestController
@RequestMapping("/privateRegions")
public class RegionPrivateControlador {
    @Autowired
    private RegionPrivateService regionService;

    // Endpoint para obtener región por ID
    @GetMapping("/{id}")
    public com.main.comunicacion.openD.DTOs.RegionPrivateDTO obtenerRegionPorId(@PathVariable Long id) {
        return regionService.obtenerRegionPorId(id);
    }

    // Endpoint para obtener todas las regiones
    @GetMapping
    public List<com.main.comunicacion.openD.DTOs.RegionPrivateDTO> obtenerRegiones() {
        return regionService.obtenerRegiones();
    }

    @GetMapping("/soloRegiones")
     public List<RegionPrivateDTO> obtenerRegionesSinCamaras() {
        return regionService.obtenerSoloRegiones();
        
    }
}
