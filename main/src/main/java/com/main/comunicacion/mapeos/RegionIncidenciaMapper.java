package com.main.comunicacion.mapeos;

import com.main.comunicacion.privadas.DTOs.RegionIncidenciaDTO;
import com.main.modelo.entidades.Incidencia;

public class RegionIncidenciaMapper {
    public static RegionIncidenciaDTO toRegionIncidenciaDTO(Incidencia incidencia) {
        return new RegionIncidenciaDTO(
                incidencia.getId(),
                incidencia.getLatitud(),
                incidencia.getLongitud(),
                incidencia.getCausa(),
                incidencia.getNivelIncidencia(),
                incidencia.getCarretera(),
                incidencia.getFechaInicio()
        );
    }
}
