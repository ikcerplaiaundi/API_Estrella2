package com.main.comunicacion.privadas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.servicios.LoginServicio;
import com.main.modelo.entidades.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(path="")
public class LoginController {

    @Autowired
    private LoginServicio loginServicio;

    /**
     * Inicia sesión con un nombre de usuario y contraseña.
     *
     * @param loginRequest Detalles de la solicitud de inicio de sesión.
     * @return Respuesta con el rol del usuario si el inicio de sesión es exitoso.
     */
    @Operation(
        summary = "Inicio de sesión",
        description = "Inicia sesión con el nombre de usuario y contraseña proporcionados. Devuelve el rol del usuario si el inicio de sesión es exitoso.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Inicio de sesión exitoso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Faltan datos de usuario o contraseña", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "401", 
                description = "Usuario o contraseña incorrectos", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "500", 
                description = "Error interno del servidor", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping(path="/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            if (loginRequest.getNombre() == null || loginRequest.getContraseña() == null) {
                return ResponseEntity.status(400).body("El nombre de usuario y la contraseña son requeridos.");
            }

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

    /**
     * Inicia sesión para usuarios de la aplicación Android.
     *
     * @param loginRequest Detalles de la solicitud de inicio de sesión desde la aplicación Android.
     * @return Respuesta con el rol y el ID del usuario si el inicio de sesión es exitoso.
     */
    @Operation(
        summary = "Inicio de sesión en Android",
        description = "Inicia sesión en la aplicación Android con el nombre de usuario y contraseña proporcionados. Devuelve el rol y el ID del usuario si el inicio de sesión es exitoso.",
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "Inicio de sesión exitoso en Android",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseAndroid.class))
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Faltan datos de usuario o contraseña", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "401", 
                description = "Usuario o contraseña incorrectos", 
                content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                responseCode = "500", 
                description = "Error interno del servidor", 
                content = @Content(mediaType = "application/json")
            )
        }
    )
    @PostMapping(path="/loginAndroid")
    public ResponseEntity<?> loginAndroid(@RequestBody LoginRequestAndroid loginRequest) {
        try {
            if (loginRequest.getNombre() == null || loginRequest.getContraseña() == null) {
                return ResponseEntity.status(400).body("El nombre de usuario y la contraseña son requeridos.");
            }

            Usuario usuario = loginServicio.login(loginRequest.getNombre(), loginRequest.getContraseña());

            if (usuario != null) {
                String rolNombre = usuario.getRol() != null ? usuario.getRol().getName() : "Sin rol";
                long idUser = usuario.getId();
                LoginResponseAndroid loginResponse = new LoginResponseAndroid(rolNombre, idUser);
                return ResponseEntity.ok(loginResponse);
            }

            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en el servidor: " + e.getMessage());
        }
    }
}

// Constructor de la solicitud de login
@Data
class LoginRequest {
    private String nombre;
    private String contraseña;

    @Override
    public String toString() {
        return "LoginRequest{nombre='" + nombre + "', contraseña='" + contraseña + "'}";
    }
}

// Constructor de la respuesta del login
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

// Constructor de la solicitud de login Android
@Data
class LoginRequestAndroid {
    private String nombre;
    private String contraseña;

    @Override
    public String toString() {
        return "LoginRequestAndroid{nombre='" + nombre + "', contraseña='" + contraseña + "'}";
    }
}

// Constructor de la respuesta del login Android
class LoginResponseAndroid {
    private String rol;
    private long id;

    public LoginResponseAndroid(String rol, long id) {
        this.rol = rol;
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
