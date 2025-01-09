package com.main.comunicacion.mapeos;

import java.util.stream.Collectors;

import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.modelo.entidades.Region;

public class RegionPrivateMapper {

    // Método para mapear Region a RegionPrivateDTO
    public static RegionPrivateDTO toRegionDTO(Region region) {
        return new RegionPrivateDTO(
                region.getId(),
                region.getIdRegion(),
                region.getNombreEs(),
                region.getNombreEu(),
                region.getCameras().stream()
                        .map(CameraMap::toDTO) 
                        .collect(Collectors.toList()),
                region.getIncidencias().stream()
                        .map(RegionIncidenciaMapper::toRegionIncidenciaDTO)
                        .collect(Collectors.toList())
        );
    }
}
