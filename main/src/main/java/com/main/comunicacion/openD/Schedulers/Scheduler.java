package com.main.comunicacion.openD.Schedulers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.servicios.CameraService;
import com.main.comunicacion.openD.servicios.RegionService;
import com.main.utils.SSLUtils;
import jakarta.annotation.PostConstruct;
@Component
public class Scheduler {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private RegionService regionService; 

    @Autowired
    private RolUsuarioScheduler RUScheduler;

    // Ejecutar el método al inicio de la aplicación
    @PostConstruct
    public void setUpOfImportData() {
        System.out.println("Ejecutando actualización inicial de datos...");
        
        // Crear roles de usuario y usuario
        RUScheduler.crearRolesUsuarios();
        // Sincronizar datos de regiones
        regionService.fetchAndSaveAllRegions();
        // Sincronizar datos de camaras
        cameraService.fetchAndSaveAllCameras();
        
        
        // Agregar más servicios si es necesario
        System.out.println("Actualización inicial de datos completada.");
    }

}
