package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.modelo.entidades.Camera;

@Component
public class CameraMap {

    // Metodo para mapear CameraDTO a Camera
    public static Camera toEntity(CameraDTO dto) {
        Camera camera = new Camera();
        
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

    
}
