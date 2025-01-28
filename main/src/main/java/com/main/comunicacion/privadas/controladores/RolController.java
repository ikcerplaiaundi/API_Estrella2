package com.main.comunicacion.privadas.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.modelo.entidades.Rol;
import com.main.modelo.repositorios.RolRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

// Gestión de peticiones de la API interna de roles
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepositorio rolRepositorio;

    /**
     * Ruta para obtener todos los roles.
     *
     * @return Lista de todos los roles disponibles.
     */
    @Operation(
        summary = "Obtener todos los roles",
        description = "Este endpoint devuelve la lista completa de roles disponibles en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de roles obtenida exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rol.class))
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @GetMapping
    public ResponseEntity<List<Rol>> obtenerRoles() {
        List<Rol> roles = rolRepositorio.findAll();
        return ResponseEntity.ok(roles);
    }
}
