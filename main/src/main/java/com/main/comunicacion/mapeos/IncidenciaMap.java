package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.modelo.entidades.Incidencia;

@Component
public class IncidenciaMap {
    

    
    public static Incidencia toEntity(IncidenciaDTO incidenciaDTO) {
        System.out.println("Hola");
        Incidencia incidencia = new Incidencia();
        incidencia.setLatitud(incidenciaDTO.getLatitude());
        incidencia.setLongitud(incidenciaDTO.getLongitude());  
        incidencia.setCausa(incidenciaDTO.getCause());
        incidencia.setNivelIncidencia(incidenciaDTO.getIncidenceLevel());
        incidencia.setCarretera(incidenciaDTO.getRoad());
        incidencia.setFechaInicio(incidenciaDTO.getStartDate());
        incidencia.setIdRegion(incidenciaDTO.getSourceId());
        return incidencia;
    }

    
   



}
