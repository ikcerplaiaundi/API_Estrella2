package com.main.comunicacion.mapeos;

import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.modelo.entidades.Camera;

//Mapeos de camara de solicitudes internas API
public class CameraPrivateMapper {

    //Mapeador camara solicitud usuario interna
    public static CameraPrivateDTO toCameraDTO(Camera camera) {
        if (camera == null) {
            return null;
        }

        return new CameraPrivateDTO(
            camera.getId(),
            camera.getCameraName(),
            camera.getLatitud(),
            camera.getLongitud(),
            camera.getUrlImage(),
            camera.getRegion().getIdRegion()
        );
    }
}