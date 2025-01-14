package com.main.comunicacion.mapeos;


import org.springframework.stereotype.Component;

import com.main.comunicacion.privadas.DTOs.CiudadPrivateDTO;
import com.main.modelo.entidades.Ciudad;

//Mapeos de ciudad de solicitudes internas API
@Component
public class CiudadPrivateMapper {

    public static CiudadPrivateDTO toCiudadDTO(Ciudad ciudad) {
        if (ciudad == null) {
            return null;
        }

        return new CiudadPrivateDTO(
                ciudad.getId(),
                ciudad.getNombre(),
                ciudad.getLatitud(),
                ciudad.getLongitud()
        );
    }
}
