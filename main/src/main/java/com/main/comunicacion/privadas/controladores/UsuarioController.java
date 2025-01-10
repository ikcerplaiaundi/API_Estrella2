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
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioService usuarioService;

    // Obtener la lista de todos los usuarios
    @GetMapping("/api/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        // Recupera todos los usuarios y los convierte en DTOs
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCorreo(),
                        usuario.getContraseña(),
                        usuario.getRol().getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO); // Responde con la lista de usuarios
    }
    /*
     * Ejemplo de petición:
     * GET http://localhost:8080/api/usuarios
     * Respuesta exitosa:
     * [
     * {
     * "id": 1,
     * "nombre": "Juan Perez",
     * "correo": "juan.perez@example.com",
     * "contraseña": "12345",
     * "rol": "ADMIN"
     * },
     * {
     * "id": 2,
     * "nombre": "Maria Lopez",
     * "correo": "maria.lopez@example.com",
     * "contraseña": "67890",
     * "rol": "USER"
     * }
     * ]
     */

    // Actualizar los datos de un usuario
    @PutMapping("/api/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        // Busca el usuario por ID
        Optional<Usuario> usuario_nuevo = usuarioRepositorio.findById(id);

        if (usuario_nuevo.isEmpty()) {
            return ResponseEntity.notFound().build(); // Responde con 404 si no se encuentra
        }

        // Actualiza los datos del usuario
        Usuario usuario = usuario_nuevo.get();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setContraseña(usuarioDTO.getContraseña());

        Usuario usuario_updated = usuarioRepositorio.save(usuario);

        // Convierte el usuario actualizado a DTO
        UsuarioDTO updatedUsuarioDTO = new UsuarioDTO(
                usuario_updated.getId(),
                usuario_updated.getNombre(),
                usuario_updated.getCorreo(),
                usuario_updated.getContraseña(),
                usuario_updated.getRol().getName());

        return ResponseEntity.ok(updatedUsuarioDTO); // Responde con el usuario actualizado
    }
    /*
     * Ejemplo de petición:
     * PUT http://localhost:8080/api/usuarios/1
     * Body (JSON):
     * {
     * "nombre": "Juan Actualizado",
     * "correo": "juan.actualizado@example.com",
     * "contraseña": "nuevaPassword123",
     * "rol": "ADMIN"
     * }
     * Respuesta exitosa:
     * {
     * "id": 1,
     * "nombre": "Juan Actualizado",
     * "correo": "juan.actualizado@example.com",
     * "contraseña": "nuevaPassword123",
     * "rol": "ADMIN"
     * }
     */

    // Eliminar un usuario por su ID
    @DeleteMapping("/api/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id.intValue()); // Llama al servicio para eliminar
            return ResponseEntity.ok("Usuario eliminado"); // Responde con éxito
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Error al eliminar el usuario"); // Responde con error
        }
    }
    /*
     * Ejemplo de petición:
     * DELETE http://localhost:8080/api/usuarios/1
     * Respuesta exitosa:
     * "Usuario eliminado"
     * Respuesta de error:
     * "Error al eliminar el usuario"
     */
}
