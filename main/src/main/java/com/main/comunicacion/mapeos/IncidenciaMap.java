package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.modelo.entidades.Incidencia;

//Mapeos de incidencia de solicitudes externas OPEN DATA
@Component
public class IncidenciaMap {
    

    //Mapeo de incidencia DTO a incidencia
    public static Incidencia toEntity(IncidenciaDTO incidenciaDTO) {
        
        Incidencia incidencia = new Incidencia();
        incidencia.setLatitud(incidenciaDTO.getLatitude());
        incidencia.setLongitud(incidenciaDTO.getLongitude());  
        incidencia.setCausa(incidenciaDTO.getCause());
        incidencia.setNivelIncidencia(incidenciaDTO.getIncidenceLevel());
        incidencia.setCarretera(incidenciaDTO.getRoad());
        incidencia.setFechaInicio(incidenciaDTO.getStartDate());
        return incidencia;
    }


}
