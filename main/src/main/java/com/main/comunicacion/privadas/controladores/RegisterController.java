package com.main.comunicacion.privadas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.servicios.RegisterService;
import com.main.modelo.entidades.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

// Gestión de peticiones de la API interna de registro
@RestController
@RequestMapping
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * Ruta para registrar a un usuario.
     *
     * @param registerRequest Los datos del usuario a registrar.
     * @return Respuesta con el estado del registro.
     */
    @Operation(
        summary = "Registrar usuario",
        description = "Permite registrar un usuario con nombre, correo y contraseña.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Registro exitoso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponse.class))
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Error al registrar el usuario", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "500", 
                description = "Error interno del servidor", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegisterRequest registerRequest) {
        try {
            Usuario usuario = registerService.registrarUsuario(registerRequest.getNombre(), registerRequest.getCorreo(), registerRequest.getContraseña());
            if (usuario != null) {
                return ResponseEntity.ok(new RegisterResponse("Registro exitoso"));
            }
            return ResponseEntity.status(400).body("Error al registrar usuario");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en el servidor: " + e.getMessage());
        }
    }

    /**
     * Ruta para registrar a un usuario en Android.
     *
     * @param registerRequest Los datos del usuario a registrar en Android.
     * @return Respuesta con el estado del registro en Android.
     */
    @Operation(
        summary = "Registrar usuario Android",
        description = "Permite registrar un usuario en la plataforma Android con nombre, correo y contraseña.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Registro exitoso en Android",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponse.class))
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Error al registrar el usuario Android", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "500", 
                description = "Error interno del servidor", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping("/registroAndroid")
    public ResponseEntity<?> registrarAndroid(@RequestBody RegisterRequest registerRequest) {
        try {
            Usuario usuario = registerService.registrarUsuarioAndroid(registerRequest.getNombre(), registerRequest.getCorreo(), registerRequest.getContraseña());
            if (usuario != null) {
                return ResponseEntity.ok(new RegisterResponse("Registro exitoso"));
            }
            return ResponseEntity.status(400).body("Error al registrar usuario");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en el servidor: " + e.getMessage());
        }
    }
}

// Constructor de la petición de registro
class RegisterRequest {
    private String nombre;
    private String correo;
    private String contraseña;

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }
}

// Constructor de la respuesta de registro
class RegisterResponse {
    private String mensaje;

    public RegisterResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
