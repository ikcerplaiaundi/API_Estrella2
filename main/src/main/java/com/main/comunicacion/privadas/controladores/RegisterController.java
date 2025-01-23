package com.main.comunicacion.privadas.controladores;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.comunicacion.privadas.servicios.RegisterService;
import com.main.modelo.entidades.Usuario;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping
public class RegisterController {

    @Autowired
    private RegisterService registerService;

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

    @PostMapping("/registroAndroid")
    public ResponseEntity<?> registrarAndroid(@RequestBody RegisterRequest registerRequest) {
        try {
            Usuario usuario = registerService.registrarUsuarioAndroid(registerRequest.getNombre(), registerRequest.getCorreo(), registerRequest.getContraseña());
            if (usuario != null) {
                return ResponseEntity.ok(new RegisterResponse("Registro exitoso"));
            }

            final String email = "codetechsoporte@gmail.com";
            final String password = "cngh uyro ilzd htfo"; 

            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(registerRequest.getCorreo()));
                message.setSubject("Registro de usuario en Estrella2");
                message.setContent("<h1>Registro exitoso</h1><p>Este es un correo de prueba</p>", "text/html");

                System.out.println("Enviando...");
                Transport.send(message);
                System.out.println("Mensaje enviado de forma exitosa...");
            } catch (MessagingException me) {
                System.out.println("Error al enviar el correo: " + me.getMessage());
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
