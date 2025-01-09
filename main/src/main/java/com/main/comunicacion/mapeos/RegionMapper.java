package com.main.comunicacion.mapeos;

import com.main.comunicacion.openD.DTOs.RegionDTO;
import com.main.modelo.entidades.Region;
import org.springframework.stereotype.Component;

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