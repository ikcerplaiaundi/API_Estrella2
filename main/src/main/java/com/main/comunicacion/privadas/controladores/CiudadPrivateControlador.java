package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.CiudadPrivateDTO;
import com.main.comunicacion.privadas.servicios.CiudadPrivateService;


//Gestion de peticiones de la api interna de ciudades
@RestController
@RequestMapping("")
public class CiudadPrivateControlador {

    private final CiudadPrivateService ciudadPrivateService;

    
    public CiudadPrivateControlador(CiudadPrivateService ciudadPrivateService) {
        this.ciudadPrivateService = ciudadPrivateService;
    }

    //Ruta con la optenemos todas las ciudades
    @GetMapping("/ciudades")
    public ResponseEntity<List<CiudadPrivateDTO>> obtenerCiudades() {
        List<CiudadPrivateDTO> ciudades = ciudadPrivateService.obtenerCiudades();
        if (ciudades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ciudades);
    }
}
