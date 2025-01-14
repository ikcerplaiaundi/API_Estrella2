package com.main.comunicacion.openD.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;

//Enpintoins de camaras con opendata
@RestController
@RequestMapping("/api/cameras")
public class CameraController {

    @Autowired
    private CameraRepository cameraRepository;

    @GetMapping
    public ResponseEntity<List<Camera>> getAllCameras() {
        List<Camera> cameras = cameraRepository.findAll();
        return ResponseEntity.ok(cameras);
    }
}