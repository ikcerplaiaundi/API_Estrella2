package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.RegionDTO;
import com.main.modelo.entidades.Region;

//Mapeos de Region de solicitudes externas OPEN DATA
@Component
public class RegionMapper {

    public Region toEntity(RegionDTO dto) {
        if (dto == null) {
            return null;
        }

        Region region = new Region();
        region.setIdRegion(dto.getId());
        region.setNombreEs(dto.getDescripcionEs());
        region.setNombreEu(dto.getDescripcionEu());

        return region;
    }
}