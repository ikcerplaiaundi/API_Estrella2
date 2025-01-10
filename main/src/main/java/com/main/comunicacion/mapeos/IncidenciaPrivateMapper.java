package com.main.comunicacion.mapeos;

import com.main.comunicacion.privadas.DTOs.IncidenciaActualizarDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaCiudadPrivateDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaConReferenciasDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaRegionPrivateDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaTipoIncidenciaPrivateDTO;
import com.main.modelo.entidades.Incidencia;

public class IncidenciaPrivateMapper {

    // Convertir una incidencia a un DTO con las referencias completas (Ciudad, Región, TipoIncidencia)
    public static IncidenciaConReferenciasDTO toDTOConReferencias(Incidencia incidencia) {
        // Verificar que las entidades no sean null antes de crear los DTOs
        IncidenciaCiudadPrivateDTO ciudadDTO = incidencia.getCiudad() != null ? 
                new IncidenciaCiudadPrivateDTO(
                    incidencia.getCiudad().getId(),
                    incidencia.getCiudad().getNombre()) : null;
    
        IncidenciaRegionPrivateDTO regionDTO = incidencia.getRegion() != null ? 
                new IncidenciaRegionPrivateDTO(
                    incidencia.getRegion().getId(),
                    incidencia.getRegion().getNombreEs(),
                    incidencia.getRegion().getNombreEu()) : null;
    
        IncidenciaTipoIncidenciaPrivateDTO tipoIncidenciaDTO = incidencia.getTipoIncidencia() != null ? 
                new IncidenciaTipoIncidenciaPrivateDTO(
                    incidencia.getTipoIncidencia().getId(),
                    incidencia.getTipoIncidencia().getNombre()) : null;
    
        return new IncidenciaConReferenciasDTO(
                incidencia.getId(),
                incidencia.getLatitud(),
                incidencia.getLongitud(),
                incidencia.getCausa(),
                incidencia.getNivelIncidencia(),
                incidencia.getCarretera(),
                incidencia.getFechaInicio(),
                ciudadDTO,
                regionDTO,
                tipoIncidenciaDTO
        );
    }

    
    // Convertir un IncidenciaActualizarDTO a una entidad Incidencia
    public static Incidencia toEntity(IncidenciaActualizarDTO incidenciaDTO) {
        Incidencia incidencia = new Incidencia();
        incidencia.setId(incidenciaDTO.getId());
        incidencia.setLatitud(incidenciaDTO.getLatitud());
        incidencia.setLongitud(incidenciaDTO.getLongitud());
        incidencia.setCausa(incidenciaDTO.getCausa());
        incidencia.setNivelIncidencia(incidenciaDTO.getNivelIncidencia());
        incidencia.setCarretera(incidenciaDTO.getCarretera());
        incidencia.setFechaInicio(incidenciaDTO.getFechaInicio());
        return incidencia;
    }

    // Actualizar una Incidencia a partir de un IncidenciaActualizarDTO (la que guarda ids y no objetos)
    public static void updateEntityFromDTO(IncidenciaActualizarDTO incidenciaDTO, Incidencia incidencia) {
        incidencia.setLatitud(incidenciaDTO.getLatitud());
        incidencia.setLongitud(incidenciaDTO.getLongitud());
        incidencia.setCausa(incidenciaDTO.getCausa());
        incidencia.setNivelIncidencia(incidenciaDTO.getNivelIncidencia());
        incidencia.setCarretera(incidenciaDTO.getCarretera());
        incidencia.setFechaInicio(incidenciaDTO.getFechaInicio());
    }
}
