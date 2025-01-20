package com.main.comunicacion.mapeos;


import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Region;


//Mapeos de camara de solicitudes internas API
public class CameraPrivateMapper {

    //Mapeador camara solicitud usuario interna
    public static CameraPrivateDTO toCameraDTO(Camera camera) {
        if (camera == null) {
            return null;
        }

        // Mapear Region a RegionPrivateDTO si existe
        Region region = camera.getRegion();
        RegionPrivateDTO regionPrivateDTO = region != null ? RegionPrivateMapper.toRegionOnlyDTO(region) : null;

        // Crear CameraPrivateDTO
        return new CameraPrivateDTO(
            camera.getId(),
            camera.getCameraName(),
            camera.getLatitud(),
            camera.getLongitud(),
            camera.getUrlImage(),
            regionPrivateDTO
        );
}
}