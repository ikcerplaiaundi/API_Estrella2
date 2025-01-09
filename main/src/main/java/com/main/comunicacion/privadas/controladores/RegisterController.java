package com.main.comunicacion.privadas.controladores;
import com.main.comunicacion.privadas.servicios.LoginServicio;
import com.main.comunicacion.privadas.servicios.RegisterService;
import com.main.modelo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

@RestController
@RequestMapping(path = "/api/registro")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

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

