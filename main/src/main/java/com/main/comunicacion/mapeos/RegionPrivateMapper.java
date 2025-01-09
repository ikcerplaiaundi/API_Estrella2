package com.main.comunicacion.mapeos;

import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.comunicacion.privadas.DTOs.RegionIncidenciaDTO;
import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.modelo.entidades.Region;

import java.util.stream.Collectors;

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
