package com.main.comunicacion.openD.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.modelo.entidades.Rol;
import com.main.modelo.repositorios.RolRepositorio;

import java.util.List;
@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping
    public ResponseEntity<List<Rol>> obtenerRoles() {
        List<Rol> roles = rolRepositorio.findAll();
        return ResponseEntity.ok(roles);
    }
}
