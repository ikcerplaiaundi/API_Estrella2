package com.main.comunicacion.privadas.controladores;

import com.main.comunicacion.privadas.servicios.LoginServicio;
import com.main.modelo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "")
public class LoginController {

    @Autowired
    private LoginServicio loginServicio;

    /**
     * Endpoint para realizar el login de un usuario
     * 
     * @param loginRequest Contiene el nombre de usuario y la contraseña
     * @return Respuesta con el rol del usuario si la autenticación es exitosa, o un mensaje de error
     */
    @Operation(summary = "Realizar login", description = "Autentica al usuario con su nombre y contraseña y devuelve el rol del usuario.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login exitoso"),
        @ApiResponse(responseCode = "400", description = "El nombre de usuario y la contraseña son requeridos"),
        @ApiResponse(responseCode = "401", description = "Usuario o contraseña incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error en el servidor")
    })
    @PostMapping
    @RequestMapping(path = "/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            if (loginRequest.getNombre() == null || loginRequest.getContraseña() == null) {
                return ResponseEntity.status(400).body("El nombre de usuario y la contraseña son requeridos.");
            }

            System.out.println("Solicitud recibida: " + loginRequest);
            Usuario usuario = loginServicio.login(loginRequest.getNombre(), loginRequest.getContraseña());

            if (usuario != null) {
                String rolNombre = usuario.getRol() != null ? usuario.getRol().getName() : "Sin rol";
                LoginResponse loginResponse = new LoginResponse(rolNombre);
                return ResponseEntity.ok(loginResponse);
            }

            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en el servidor: " + e.getMessage());
        }
    }
}

@Data
class LoginRequest {
    private String nombre;
    private String contraseña;

    @Override
    public String toString() {
        return "LoginRequest{nombre='" + nombre + "', contraseña='" + contraseña + "'}";
    }
}

class LoginResponse {
    private String rol;

    public LoginResponse(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
