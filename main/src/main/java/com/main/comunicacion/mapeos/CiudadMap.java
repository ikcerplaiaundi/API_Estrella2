package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.openD.DTOs.CiudadDTO;
import com.main.modelo.entidades.Ciudad;

//Mapeos de ciudad de solicitudes externas OPEN DATA
@Component
public class CiudadMap {

    //Método para mapear CameraDTO (Solicitud externa) a Camera (ya existente)
     public static Ciudad toEntity(CiudadDTO ciudadDTO) {
        
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(ciudadDTO.getName());
        ciudad.setLatitud(ciudadDTO.getLat());
        ciudad.setLongitud(ciudadDTO.getLon());
        
        return ciudad;
    }

}
