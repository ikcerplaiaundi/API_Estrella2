package com.main.comunicacion.privadas.servicios;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.main.modelo.entidades.Rol;
import com.main.modelo.entidades.Usuario;
import com.main.modelo.repositorios.RolRepositorio;
import com.main.modelo.repositorios.UsuarioRepositorio;

//Servicios que ofrecen todas las solicitudes de registro a la api interna
@Service
public class RegisterService {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private RolRepositorio rolRepositorio;

    //Servicio con el cual registraremos a un usuario
    public Usuario registrarUsuario(String nombre, String email, String contraseña) {
        validarUsuarioUnico(nombre, email);
        Rol adminRol = obtenerOCrearRol("administrador");
        
        Usuario nuevoUsuario = crearNuevoUsuario(nombre, email, hashearContraseña(contraseña), adminRol);
        return usuarioRepository.save(nuevoUsuario);
    }

    //Servicio con el cual registraremos a un usuario en android
    public Usuario registrarUsuarioAndroid(String nombre, String email, String contraseña) {
        validarUsuarioUnico(nombre, email);
        Rol usuarioRol = obtenerOCrearRol("usuario");

        enviarEmail(email);


        
        Usuario nuevoUsuario = crearNuevoUsuario(nombre, email, hashearContraseña(contraseña), usuarioRol);
        return usuarioRepository.save(nuevoUsuario);
    }

    //Servicio con el cual validaremos a un usuario
    private void validarUsuarioUnico(String nombre, String email) {
        if (usuarioRepository.existsByCorreo(email)) {
            throw new RuntimeException("El correo ya está registrado");
        }
        if (usuarioRepository.existsByNombre(nombre)) {
            throw new RuntimeException("Ya existe el nombre de usuario");
        }
    }

    //Servicio con el cual obtenedremos un rol
    private Rol obtenerOCrearRol(String nombreRol) {
        return rolRepositorio.findByName(nombreRol)
                .orElseGet(() -> rolRepositorio.save(new Rol(nombreRol)));
    }

    //Servicio con el cual crearemos un nuevo usuario
    private Usuario crearNuevoUsuario(String nombre, String email, String contraseña, Rol rol) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(email);
        nuevoUsuario.setContraseña(contraseña);
        nuevoUsuario.setRol(rol);
        return nuevoUsuario;
    }

    //Funcion con la que podremos hasear contraseñas
    private String hashearContraseña(String contraseña) {
        return DigestUtils.md5DigestAsHex(contraseña.getBytes(StandardCharsets.UTF_8));
    }


    //Funcion para enviar email
    private void enviarEmail(String emailEnvio){
        System.out.println("emailEnvio "+ emailEnvio);
         //Configuramos el correo y la contraseña
        final String password = "fjob zeja nnap uybc";
        final String toEmail = "codetechsoporte@gmail.com";

        System.out.println("Iniciamos TLSEmail");
        Properties props = new Properties();
        //Configuramos todos los parámetros de corre
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable", "true"); 
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(toEmail, password);

            }
        });

        try {
            //Envio destinatario
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailEnvio, true));
            //Cuerpo mensaje 
            message.setSubject("Bienvenido a Estrellados");
            message.setText("Gracias Por registrarte en nuestra App");
            System.out.println("enviando...");
            Transport.send(message);
            System.out.println("Mensaje enviado de forma exitosa....");

        } catch (MessagingException me) {
            System.out.println("Exception: " + me);

        }
    }
    
}
