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

        RegionPrivateDTO regionDTO = null;
        if (camera.getRegion() != null) {
            Region region = regionRepository.findById(camera.getRegion().getId()).orElse(null);
            if (region != null) {
                regionDTO = new RegionPrivateDTO(region.getId(),
                region.getIdRegion(),
                region.getNombreEs(),
                region.getNombreEu());
            }
        }

        return new CameraPrivateDTO(
            camera.getId(),
            camera.getCameraName(),
            camera.getLatitud(),
            camera.getLongitud(),
            camera.getUrlImage(),
            regionDTO
        );
    }
}