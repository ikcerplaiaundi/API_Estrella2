package com.main.comunicacion.openD;

import com.main.comunicacion.openD.servicios.CameraService;
import com.main.main.SSLUtils;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    // Ejecutar el método al inicio de la aplicación
    @PostConstruct
    public void setUpOfImportData() {
        System.out.println("Ejecutando actualización inicial de cámaras...");
        cameraService.fetchAndSaveAllCameras();
        //resto de peticiones
    }
}
