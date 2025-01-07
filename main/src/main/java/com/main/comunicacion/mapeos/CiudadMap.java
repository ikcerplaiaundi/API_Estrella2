package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.CiudadDTO;
import com.main.modelo.entidades.Ciudad;


@Component
public class CiudadMap {

     public static Ciudad toEntity(CiudadDTO ciudadDTO) {
        
        Ciudad incidencia = new Ciudad();
        incidencia.setNombre(ciudadDTO.getName());
        incidencia.setLatitud(ciudadDTO.getLat());
        incidencia.setLongitud(ciudadDTO.getLon());
        
        return incidencia;
    }

}
