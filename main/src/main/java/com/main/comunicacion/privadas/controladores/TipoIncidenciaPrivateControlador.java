package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.TipoIncidenciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.TipoIncidenciaPrivateService;


//Gestion de peticiones de la api interna de tipos de incidencia
@RestController
@RequestMapping("")
public class TipoIncidenciaPrivateControlador {

    private final TipoIncidenciaPrivateService tipoIncidenciaPrivateService;

    
    public TipoIncidenciaPrivateControlador(TipoIncidenciaPrivateService tipoIncidenciaPrivateService) {
        this.tipoIncidenciaPrivateService = tipoIncidenciaPrivateService;
    }

    @GetMapping("/tiposIncidencias")
    public ResponseEntity<List<TipoIncidenciaPrivateDTO>> obtenerProvincias() {
        List<TipoIncidenciaPrivateDTO> tiposIncidencia = tipoIncidenciaPrivateService.obtenerTiposIncidencias();
        if (tiposIncidencia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tiposIncidencia);
    }
}
