package com.main.comunicacion.mapeos;


import org.springframework.stereotype.Component;

import com.main.comunicacion.privadas.DTOs.TipoIncidenciaPrivateDTO;
import com.main.modelo.entidades.TipoIncidencia;

@Component
public class TipoIncidenciaPrivateMapper {

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
