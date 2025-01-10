package com.main.comunicacion.privadas.controladores;

import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.comunicacion.privadas.servicios.RegionPrivateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/privateRegions")
public class RegionPrivateControlador {
    @Autowired
    private RegionPrivateService regionService;

    // Endpoint para obtener región por ID
    @GetMapping("/{id}")
    public RegionPrivateDTO obtenerRegionPorId(@PathVariable Long id) {
        return regionService.obtenerRegionPorId(id);
    }

    // Endpoint para obtener todas las regiones
    @GetMapping
    public List<RegionPrivateDTO> obtenerRegiones() {
        return regionService.obtenerRegiones();
    }
}
