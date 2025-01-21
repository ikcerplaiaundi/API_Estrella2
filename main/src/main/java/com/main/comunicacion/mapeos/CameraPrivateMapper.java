package com.main.comunicacion.mapeos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.RegionDTO;
import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Region;
import com.main.modelo.repositorios.RegionRepository;

//Mapeos de camara de solicitudes internas API
@Component
public class CameraPrivateMapper {

    @Autowired
    private RegionRepository regionRepository;

    public CameraPrivateDTO toCameraDTO(Camera camera) {
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