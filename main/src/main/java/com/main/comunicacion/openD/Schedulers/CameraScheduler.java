package com.main.comunicacion.openD.Schedulers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.servicios.CameraService;
import com.main.utils.SSLUtils;

import jakarta.annotation.PostConstruct;
@Component
public class CameraScheduler {

    @Autowired
    private CameraService cameraService;

    // Ejecutar el metodo cada 30 minutos
    @Scheduled(fixedRate = 1800000) // 30 minutos en milisegundos
    public void fetchAndSaveCameras() {
        System.out.println("Ejecutando actualización de cámaras...");
        cameraService.fetchAndSaveAllCameras();
    }
}
