package com.main.comunicacion.mapeos;

import com.main.modelo.entidades.Camera;
import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;

public class CameraPrivateMapper {

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