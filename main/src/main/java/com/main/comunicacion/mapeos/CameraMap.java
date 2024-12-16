package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.modelo.entidades.Camera;

@Component
public class CameraMap{

    public Camera toEntity(CameraDTO dto) {
        Camera camera = new Camera();
        camera.setCameraId(dto.getCameraId());
       //resto
        return camera;
    }
    public CameraDTO toDTO(Camera camera) {
        CameraDTO dto = new CameraDTO();
        dto.setCameraId(camera.getCameraId());
        //resto
        return dto;
    }
}
