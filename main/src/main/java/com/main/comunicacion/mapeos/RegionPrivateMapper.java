package com.main.comunicacion.mapeos;

import java.util.stream.Collectors;

import com.main.comunicacion.openD.DTOs.RegionPrivateDTO;
import com.main.modelo.entidades.Region;

//Mapeos de Region de solicitudes internas API
public class RegionPrivateMapper {

    // Método para mapear Region a RegionPrivateDTO
    public static RegionPrivateDTO toRegionDTO(Region region) {
        CameraMap cameraMap = new CameraMap(); // Create an instance of CameraMap
        return new RegionPrivateDTO(
                region.getId(),
                region.getIdRegion(),
                region.getNombreEs(),
                region.getNombreEu(),
                region.getCameras().stream()
                        .map(camera -> cameraMap.toDTO(camera))  // Use the instance to call toDTO
                        .collect(Collectors.toList())
        );
    }
}
