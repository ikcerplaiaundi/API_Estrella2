package com.main.comunicacion.mapeos;


import org.springframework.stereotype.Component;

import com.main.comunicacion.privadas.DTOs.TipoIncidenciaPrivateDTO;
import com.main.modelo.entidades.TipoIncidencia;

//Mapeos de TipoIncidencia de solicitudes internas API
@Component
public class TipoIncidenciaPrivateMapper {

    //Mapea de incidencia a incidencia DTO
    public static TipoIncidenciaPrivateDTO toTipoIncidenciaDTO(TipoIncidencia tipoIncidencia) {
        if (tipoIncidencia == null) {
            return null;
        }

        return new TipoIncidenciaPrivateDTO(
                tipoIncidencia.getId(),
                tipoIncidencia.getNombre()
        );
    }
    
}
