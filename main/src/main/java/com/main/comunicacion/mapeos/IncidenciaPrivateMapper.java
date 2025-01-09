package com.main.comunicacion.mapeos;


import org.springframework.stereotype.Component;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.modelo.entidades.Incidencia;

@Component
public class IncidenciaPrivateMapper {

    public static IncidenciaPrivateDTO toIncidenciaDTO(Incidencia incidencia) {
        if (incidencia == null) {
            return null;
        }

        return new IncidenciaPrivateDTO(
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
