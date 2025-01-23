package com.main.comunicacion.openD.Schedulers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.servicios.CameraService;


//Petinciones sincronizadas con camara
@Component
public class CameraScheduler {

    @Autowired
    private CameraService cameraService;

    
}
