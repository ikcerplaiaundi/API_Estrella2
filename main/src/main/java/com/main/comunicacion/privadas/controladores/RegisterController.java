package com.main.comunicacion.privadas.controladores;

import com.main.comunicacion.privadas.servicios.RegisterService;
import com.main.modelo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/api/registro")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * Endpoint para registrar un nuevo usuario
     * 
     * @param registerRequest Objeto que contiene los datos de registro del usuario
     * @return Respuesta indicando si el registro fue exitoso o hubo un error
     */
    @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un nuevo usuario proporcionando nombre, correo y contraseña.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registro exitoso"),
        @ApiResponse(responseCode = "400", description = "Error al registrar usuario"),
        @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @PostMapping
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
}

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

class RegisterResponse {
    private String mensaje;

    public RegisterResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
