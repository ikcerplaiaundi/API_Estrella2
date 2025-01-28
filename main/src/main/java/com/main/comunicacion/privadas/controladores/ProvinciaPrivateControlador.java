package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.ProvinciaPrivateDTO;
import com.main.comunicacion.privadas.servicios.ProvinciaPrivateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("")
public class ProvinciaPrivateControlador {

    private final ProvinciaPrivateService provinciaPrivateService;

    public ProvinciaPrivateControlador(ProvinciaPrivateService provinciaPrivateService) {
        this.provinciaPrivateService = provinciaPrivateService;
    }

    /**
     * Obtiene la lista de todas las provincias.
     *
     * @return Respuesta con la lista de todas las provincias.
     */
    @Operation(
        summary = "Obtener todas las provincias",
        description = "Devuelve la lista completa de todas las provincias.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Lista de provincias obtenida exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProvinciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontraron provincias", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping("/provincias")
    public ResponseEntity<List<ProvinciaPrivateDTO>> obtenerProvincias() {
        List<ProvinciaPrivateDTO> provincias = provinciaPrivateService.obtenerProvincias();
        if (provincias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(provincias);
    }

    /**
     * Obtiene una provincia concreta por su ID.
     *
     * @param id El ID de la provincia que se desea obtener.
     * @return Respuesta con la provincia correspondiente al ID.
     */
    @Operation(
        summary = "Obtener provincia por ID",
        description = "Devuelve la provincia correspondiente al ID proporcionado.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Provincia obtenida exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProvinciaPrivateDTO.class))
            ),
            @ApiResponse(
                responseCode = "404", 
                description = "No se encontró la provincia con el ID dado", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/filtrosProvincias/provincia")
    public ResponseEntity<?> filtroIncidenciaCiudad(@RequestParam Long id) {
        List<ProvinciaPrivateDTO> provincias = provinciaPrivateService.obtenerProvincia(id);
        if (provincias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(provincias);
    }
}
