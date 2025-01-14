package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.DTOs.ProvinciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.ProvinciaPrivateService;


//Gestion de peticiones de la api interna de provincias
@RestController
@RequestMapping("")
public class ProvinciaPrivateControlador {

    private final ProvinciaPrivateService provinciaPrivateService;

    
    public ProvinciaPrivateControlador(ProvinciaPrivateService provinciaPrivateService) {
        this.provinciaPrivateService = provinciaPrivateService;
    }

    @GetMapping("/api/provincias")
    public ResponseEntity<List<ProvinciaPrivateDTO>> obtenerProvincias() {
        List<ProvinciaPrivateDTO> provincias = provinciaPrivateService.obtenerProvincias();
        if (provincias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(provincias);
    }

    @PostMapping("/filtrosProvincias/provincia")
    public ResponseEntity<?> filtroIncidenciaCiudad(@RequestParam Long id) {
        List<ProvinciaPrivateDTO> provincias = provinciaPrivateService.obtenerProvincia(id);
        if (provincias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(provincias);
    }
}
