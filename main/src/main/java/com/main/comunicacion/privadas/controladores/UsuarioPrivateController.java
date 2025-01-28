package com.main.comunicacion.privadas.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.DTOs.RolPrivateDTO;
import com.main.comunicacion.privadas.DTOs.UsuarioDTO;
import com.main.comunicacion.privadas.servicios.UsuarioService;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.UsuarioRepositorio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

// Gestión de peticiones de la API interna de usuarios
@RestController
public class UsuarioPrivateController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Ruta para obtener todos los usuarios.
     *
     * @return Lista de usuarios en formato DTO.
     */
    @Operation(
        summary = "Obtener todos los usuarios",
        description = "Este endpoint devuelve una lista de todos los usuarios registrados en el sistema.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCorreo(),
                        usuario.getContraseña(),
                        new RolPrivateDTO(usuario.getRol().getId(), usuario.getRol().getName())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }

    /**
     * Ruta para obtener un usuario concreto por ID.
     *
     * @param id ID del usuario.
     * @return El usuario solicitado o un error 404 si no se encuentra.
     */
    @Operation(
        summary = "Obtener un usuario por ID",
        description = "Este endpoint devuelve los detalles de un usuario específico utilizando su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(id);

        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOpt.get();
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContraseña(),
                new RolPrivateDTO(usuario.getRol().getId(), usuario.getRol().getName()));

        return ResponseEntity.ok(usuarioDTO);
    }

    /**
     * Ruta para actualizar un usuario.
     *
     * @param id ID del usuario a actualizar.
     * @param usuarioDTO Datos del usuario a actualizar.
     * @return El usuario actualizado.
     */
    @Operation(
        summary = "Actualizar un usuario",
        description = "Este endpoint permite actualizar la información de un usuario existente.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @PutMapping("/usuarios/{id}")
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
                new RolPrivateDTO(usuario_updated.getRol().getId(), usuario_updated.getRol().getName()));

        return ResponseEntity.ok(updatedUsuarioDTO);
    }

    /**
     * Ruta para eliminar un usuario por ID.
     *
     * @param id ID del usuario a eliminar.
     * @return Mensaje de éxito o error en la eliminación.
     */
    @Operation(
        summary = "Eliminar un usuario",
        description = "Este endpoint permite eliminar un usuario específico usando su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar el usuario"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        }
    )
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id.intValue());
            return ResponseEntity.ok("Usuario eliminado");
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Error al eliminar el usuario");
        }
    }
}
