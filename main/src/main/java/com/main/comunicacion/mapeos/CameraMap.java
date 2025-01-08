package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.modelo.entidades.Camera;

@Component
public class CameraMap {

    // Metodo para mapear CameraDTO a Camera
    public static Camera toEntity(CameraDTO dto) {
        Camera camera = new Camera();
        camera.setCameraId(dto.getCameraId());
        //camera.setRegionId(dto.getSourceId());  // Suponiendo que 'sourceId' es la region
        camera.setCameraName(dto.getCameraName());
        camera.setLatitud(dto.getLatitude());
        camera.setLongitud(dto.getLongitude());
        camera.setCarretera(dto.getRoad());
        camera.setKilometro(dto.getKilometer());
        camera.setDireccion(dto.getAddress());
        camera.setUrlImage(dto.getUrlImage());
        // Si tiene una relacion con la entidad `Region`
        return camera;
    }

    // Método para mapear Camera a CameraDTO
    public static CameraDTO toDTO(Camera camera) {
        CameraDTO dto = new CameraDTO();
        dto.setCameraId(camera.getCameraId());
        //dto.setSourceId(camera.getRegionId());  // Suponiendo que 'regionId' se mapea como 'sourceId'
        dto.setCameraName(camera.getCameraName());
        dto.setLatitude(camera.getLatitud());
        dto.setLongitude(camera.getLongitud());
        dto.setRoad(camera.getCarretera());
        dto.setKilometer(camera.getKilometro());
        dto.setAddress(camera.getDireccion());
        dto.setUrlImage(camera.getUrlImage());
        return dto;
    }
}
