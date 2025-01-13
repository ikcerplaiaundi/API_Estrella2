package com.main.comunicacion.privadas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.modelo.entidades.Rol;
import com.main.modelo.repositorios.RolRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolRepositorio rolRepositorio;

    /**
     * Endpoint para obtener todos los roles
     * 
     * @return Lista de roles
     */
    @Operation(summary = "Obtener todos los roles", description = "Recupera una lista de todos los roles disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Roles obtenidos exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron roles")
    })
    @GetMapping
    public ResponseEntity<List<Rol>> obtenerRoles() {
        List<Rol> roles = rolRepositorio.findAll();
        return ResponseEntity.ok(roles);
    }
}
