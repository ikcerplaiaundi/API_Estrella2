package com.main.comunicacion.mapeos;

import org.springframework.stereotype.Component;

import com.main.comunicacion.privadas.DTOs.ProvinciaPrivateDTO;
import com.main.modelo.entidades.Provincia;

//Mapeos de Provincia de solicitudes internas API
@Component
public class ProvinciaPrivateMapper {

    //Mapea de provincia a provinciaPrivateDTO
    public static ProvinciaPrivateDTO toProvinciaDTO(Provincia provincia) {
        if (provincia == null) {
            return null;
        }

        return new ProvinciaPrivateDTO(
                provincia.getId(),
                provincia.getNombre(),
                provincia.getLatitud(),
                provincia.getLongitud(),
                provincia.getCiudades()
        );
    }
}
