package com.main.comunicacion.openD.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.openD.DTOs.UsuarioDTO;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.UsuarioRepositorio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/api/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
            .map(usuario -> new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                usuario.getRol().getName() 
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }


}