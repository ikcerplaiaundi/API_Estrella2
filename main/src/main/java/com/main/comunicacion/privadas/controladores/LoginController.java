package com.main.comunicacion.privadas.controladores;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;
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

    ////Ruta con la cual nos loguemos con un usuario
    @PostMapping(path="/login")

    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("login"+loginRequest);
        try {
            if (loginRequest.getNombre() == null || loginRequest.getContraseña() == null) {
                return ResponseEntity.status(400).body("El nombre de usuario y la contraseña son requeridos.");
            }

            System.out.println("Solicitud recibida: " + loginRequest);
            System.out.println("Nombre: " + loginRequest.getNombre());
            System.out.println("Nombre: " + loginRequest.getContraseña());

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


    //Ruta con la cual nos loguemos con un usuario en android
    @PostMapping(path="/loginAndroid")

    public ResponseEntity<?> loginAndroid(@RequestBody LoginRequestAndroid loginRequest) {
        System.out.println("login"+loginRequest);
        try {
            if (loginRequest.getNombre() == null || loginRequest.getContraseña() == null) {
                return ResponseEntity.status(400).body("El nombre de usuario y la contraseña son requeridos.");
            }

            System.out.println("Solicitud recibida: " + loginRequest);
            System.out.println("Nombre: " + loginRequest.getNombre());
            System.out.println("Nombre: " + loginRequest.getContraseña());

            Usuario usuario = loginServicio.login(loginRequest.getNombre(), loginRequest.getContraseña());

            if (usuario != null) {
                String rolNombre = usuario.getRol() != null ? usuario.getRol().getName() : "Sin rol";
                long idUser = usuario.getId();
                LoginResponseAndroid loginResponse = new LoginResponseAndroid(rolNombre,idUser);
                return ResponseEntity.ok(loginResponse);
            }

            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error en el servidor: " + e.getMessage());
        }
    }
}

//Contrutor de la peticion login
@Data
class LoginRequest {
    private String nombre;
    private String contraseña;

    @Override
    public String toString() {
        return "LoginRequest{nombre='" + nombre + "', contraseña='" + contraseña + "'}";
    }
}
//Construrtor de la respuesta login
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
//Contrutor de la peticion login android
@Data
class LoginRequestAndroid {
    private String nombre;
    private String contraseña;

    @Override
    public String toString() {
        return "LoginRequestAndroid{nombre='" + nombre + "', contraseña='" + contraseña + "'}";
    }
}
//Contrutor de la respuesta login android
class LoginResponseAndroid {
    private String rol;
    private long id;

    public LoginResponseAndroid(String rol,long id) {
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

