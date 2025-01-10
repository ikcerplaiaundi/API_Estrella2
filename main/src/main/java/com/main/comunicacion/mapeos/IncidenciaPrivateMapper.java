package com.main.comunicacion.mapeos;




import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.modelo.entidades.Incidencia;
public class IncidenciaPrivateMapper {

    public static Incidencia toEntity(IncidenciaPrivateDTO dto) {
        Incidencia incidencia = new Incidencia();
        updateEntityFromDTO(dto, incidencia);
        return incidencia;
    }

    public static void updateEntityFromDTO(IncidenciaPrivateDTO dto, Incidencia incidencia) {
        incidencia.setLatitud(dto.getLatitud());
        incidencia.setLongitud(dto.getLongitud());
        incidencia.setCausa(dto.getCausa());
        incidencia.setNivelIncidencia(dto.getNivelIncidencia());
        incidencia.setCarretera(dto.getCarretera());
        incidencia.setFechaInicio(dto.getFechaInicio());
    }

    public static IncidenciaPrivateDTO toDTO(Incidencia entity) {
        return new IncidenciaPrivateDTO(
                entity.getId(),
                entity.getLatitud(),
                entity.getLongitud(),
                entity.getCausa(),
                entity.getNivelIncidencia(),
                entity.getCarretera(),
                entity.getFechaInicio(),
                entity.getCiudad().getId(),
                entity.getRegion().getId(),
                entity.getTipoIncidencia().getId()
        );
    }
}
