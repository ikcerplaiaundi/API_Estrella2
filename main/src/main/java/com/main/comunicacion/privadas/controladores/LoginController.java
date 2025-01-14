package com.main.comunicacion.privadas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.servicios.LoginServicio;
import com.main.modelo.entidades.Usuario;

import lombok.Data;

//Gestion de peticiones de la api interna de login
@RestController
@RequestMapping(path="")
public class LoginController {

    @Autowired
    private LoginServicio loginServicio;

    @PostMapping
    @RequestMapping(path="/api/login")
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
