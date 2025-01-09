package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Region;

@Component
public class CameraMap {

    // Método para mapear CameraDTO a Camera (ya existente)
    public static Camera toEntity(CameraDTO dto) {
        Camera camera = new Camera();
        
        camera.setCameraName(dto.getCameraName());
        camera.setLatitud(dto.getLatitude());
        camera.setLongitud(dto.getLongitude());
        camera.setCarretera(dto.getRoad());
        camera.setKilometro(dto.getKilometer());
        camera.setDireccion(dto.getAddress());
        camera.setUrlImage(dto.getUrlImage());
        
        
        
        return camera;
    }

    // Nuevo método para mapear Camera a CameraPrivateDTO
    public static CameraPrivateDTO toDTO(Camera camera) {
        return new CameraPrivateDTO(
            camera.getId(),
            camera.getCameraName(),
            camera.getLatitud(),
            camera.getLongitud(),
            camera.getUrlImage(),
            camera.getRegion() != null ? camera.getRegion().getIdRegion() : null
        );
    }
}
