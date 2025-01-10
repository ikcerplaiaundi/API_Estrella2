package com.main.comunicacion.privadas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.main.comunicacion.privadas.DTOs.UsuarioDTO;
import com.main.comunicacion.privadas.servicios.UsuarioService;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.UsuarioRepositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UsuarioPrivateController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioService usuarioService;

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

    @PutMapping("/api/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario_nuevo = usuarioRepositorio.findById(id);
        
        if (usuario_nuevo.isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }

        Usuario usuario = usuario_nuevo.get();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContraseña(usuarioDTO.getContraseña());


        Usuario usuario_updated = usuarioRepositorio.save(usuario);

        UsuarioDTO updatedUsuarioDTO = new UsuarioDTO(
            usuario_updated.getId(),
            usuario_updated.getNombre(),
            usuario_updated.getCorreo(),
            usuario_updated.getContraseña(),
            usuario_updated.getRol().getName()
        );

        return ResponseEntity.ok(updatedUsuarioDTO); 
    }

    @DeleteMapping("/api/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id.intValue());
            return ResponseEntity.ok("Usuario eliminado");
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Error al eliminar el usuario");
        }
    }
}