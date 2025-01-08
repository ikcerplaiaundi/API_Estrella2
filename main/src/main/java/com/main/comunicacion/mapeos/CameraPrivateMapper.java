package com.main.comunicacion.mapeos;

import com.main.modelo.entidades.Camera;
import com.main.comunicacion.privadas.DTOs.CameraDTO;

public class CameraPrivateMapper {

    public static CameraDTO toCameraDTO(Camera camera) {
        if (camera == null) {
            return null;
        }

        return new CameraDTO(
            camera.getCameraId(),
            camera.getCameraName(),
            camera.getLatitud(),
            camera.getLongitud(),
            camera.getUrlImage()
        );
    }
}