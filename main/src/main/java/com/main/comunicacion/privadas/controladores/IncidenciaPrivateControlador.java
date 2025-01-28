package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTOCRUD;
import com.main.comunicacion.privadas.servicios.IncidenciaPrivateService;

import jakarta.persistence.EntityNotFoundException;
//Gestion de peticiones de la api interna de incidencias
@RestController
@RequestMapping("")
public class IncidenciaPrivateControlador {

    private final IncidenciaPrivateService incidenciaPrivateService;

    public IncidenciaPrivateControlador(IncidenciaPrivateService incidenciaPrivateService) {
        this.incidenciaPrivateService = incidenciaPrivateService;
    }

    //Ruta con la cual obtenemos todas la incidencias
    @GetMapping("/incidencias")
    public ResponseEntity<List<IncidenciaPrivateDTO>> obtenerIncidencias() {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidencias();
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    //Ruta con la cual obtenemos todas la incidencias de una ciudad
    @PostMapping("/filtrosIncidencias/ciudad")
    public ResponseEntity<?> filtroIncidenciaCiudad(@RequestParam Long idCiudad) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasCiudad(idCiudad);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    //Ruta con la cual obtenemos todas las incidencias de una provincia
    @PostMapping("/filtrosIncidencias/provincia")
    public ResponseEntity<?> filtroIncidenciaProvincia(@RequestParam Long idProvincia) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasProvincia(idProvincia);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    //Ruta con la cual obtenermos todas las incidencias de una ragion
    @PostMapping("/filtrosIncidencias/region")
    public ResponseEntity<?> filtroIncidenciaRegion(@RequestParam Long idRegion) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasRegion(idRegion);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }
    //Ruta con la cual obtenermos todas la incidencias de un tipo de incidencia
    @PostMapping("/filtrosIncidencias/tipoIncidencia")
    public ResponseEntity<?> filtroIncidenciaTipoIncidencia(@RequestParam Long idTipoIncidencia) {
        List<IncidenciaPrivateDTO> incidencias = incidenciaPrivateService.obtenerIncidenciasTipoIncidencia(idTipoIncidencia);
        if (incidencias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incidencias);
    }

    //Ruta con la cual creamos un incidencia
    @PostMapping("/crearIncidencia")
    public ResponseEntity<?> crearIncidencia(@RequestBody  IncidenciaPrivateDTOCRUD incidenciaPrivateDTOCRUD) {

        
        String mensaje = incidenciaPrivateService.crearIncidencia(incidenciaPrivateDTOCRUD);
        return ResponseEntity.ok(mensaje);

    }
   //Ruta con la cual actualizamos una incidencia
    @PutMapping("/actualizarIncidencia")
    public ResponseEntity<?> actualizarIncidencia(@RequestBody IncidenciaPrivateDTOCRUD incidenciaPrivateDTOCRUD) {
        try {
            String mensaje = incidenciaPrivateService.actualizarIncidencia(incidenciaPrivateDTOCRUD);
            return ResponseEntity.ok(mensaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Ruta con la cual eliminamos una incidencia
    @DeleteMapping("/eliminarIncidencia")
    public ResponseEntity<?> eliminarIncidencia(@RequestBody IncidenciaPrivateDTOCRUD incidenciaPrivateDTOCRUD) {
        try {
            String mensaje = incidenciaPrivateService.eliminarIncidencia(incidenciaPrivateDTOCRUD);
            return ResponseEntity.ok(mensaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}