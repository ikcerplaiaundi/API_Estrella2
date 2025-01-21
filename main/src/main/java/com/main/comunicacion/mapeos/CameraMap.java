package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.modelo.entidades.Camera;

@Component
//Mapeos de cámara de solicitudes externas OPEN DATA
public class CameraMap {

    // Método para mapear CameraDTO (Solicitud externa) a Camera (entidad existente)
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

    // Método para mapear Camera a CameraPrivateDTO
    public static CameraPrivateDTO toDTO(Camera camera) {
        return new CameraPrivateDTO(
            camera.getId(),
            camera.getCameraName(),
            camera.getLatitud(),
            camera.getLongitud(),
            camera.getUrlImage(),
            camera.getRegion() != null
                ? new RegionPrivateDTO(
                    camera.getRegion().getId(),
                    camera.getRegion().getIdRegion(),
                    camera.getRegion().getNombreEs(),
                    camera.getRegion().getNombreEu()
                )
                : null
        );
    }
    
}
