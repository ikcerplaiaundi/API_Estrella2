package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.modelo.entidades.Incidencia;

@Component
public class IncidenciaPrivateMapper {
    

    
    public static Incidencia toIncidenciaDTO(Incidencia incidencia) {
        
        if(incidencia == null){
            return null;
        }

        return new IncidenciaDTO(
            incidencia.getId();
            incidencia.getLatitud();
            incidencia.getLongitud();
            incidencia.getCausa();
            incidencia.getNivelIncidencia();
            incidencia.getCarretera();
            incidencia.getFechaInicio();

        );
        
    }


}
