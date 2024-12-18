package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Incidencia;

@Component
public class IncidenciaMap {

    
    public static Incidencia toEntity(IncidenciaDTO dto) {
        Camera camera = new Camera();
        camera.setCameraId(dto.getCameraId());
        camera.setRegionId(dto.getSourceId());  
        camera.setCameraName(dto.getCameraName());
        camera.setLatitud(dto.getLatitude());
        camera.setLongitud(dto.getLongitude());
        camera.setCarretera(dto.getRoad());
        camera.setKilometro(dto.getKilometer());
        camera.setDirecion(dto.getAddress());
        camera.setUrlImage(dto.getUrlImage());
       
        return camera;
    }

    
    public static CameraDTO toDTO(Camera camera) {
        CameraDTO dto = new CameraDTO();
        dto.setCameraId(camera.getCameraId());
        dto.setSourceId(camera.getRegionId());  
        dto.setCameraName(camera.getCameraName());
        dto.setLatitude(camera.getLatitud());
        dto.setLongitude(camera.getLongitud());
        dto.setRoad(camera.getCarretera());
        dto.setKilometer(camera.getKilometro());
        dto.setAddress(camera.getDirecion());
        dto.setUrlImage(camera.getUrlImage());
        return dto;
    }



}
