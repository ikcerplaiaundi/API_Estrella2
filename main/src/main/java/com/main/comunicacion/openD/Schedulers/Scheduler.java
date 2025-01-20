package com.main.comunicacion.openD.Schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.servicios.CameraService;
import com.main.comunicacion.openD.servicios.IncidenciaService;
import com.main.comunicacion.openD.servicios.RegionService;

import jakarta.annotation.PostConstruct;

//Llamamos a todos las peticiones que ser cargarán al iniciar la aplicación
@Component
public class Scheduler {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private RolUsuarioScheduler RUScheduler;

    @Autowired
    private IncidenciaService incidenciaService;

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

        // Sincronizar datos de incidencia
        incidenciaService.peticionIncidenciasDeLaAPIMes();

        // Agregar más servicios si es necesario
        System.out.println("Actualización inicial de datos completada.");
    }

}
