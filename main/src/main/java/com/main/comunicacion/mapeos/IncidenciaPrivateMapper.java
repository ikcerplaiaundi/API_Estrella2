package com.main.comunicacion.mapeos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.entidades.Region;
import com.main.modelo.entidades.TipoIncidencia;
import com.main.modelo.repositorios.CiudadRepositorio;
import com.main.modelo.repositorios.ProvinciaRepositorio;
import com.main.modelo.repositorios.RegionRepository;
import com.main.modelo.repositorios.TipoIncidenciaRepositorio;

@Component
public class IncidenciaPrivateMapper {

    public static CiudadRepositorio ciudadRepositorio;
    public static RegionRepository regionRepositorio;
    public static TipoIncidenciaRepositorio tipoIncidenciaRepositorio;
    public static ProvinciaRepositorio provinciaRepositorio;

    @Autowired
    public IncidenciaPrivateMapper(CiudadRepositorio ciudadRepositorio,
            RegionRepository regionRepositorio,
            TipoIncidenciaRepositorio tipoIncidenciaRepositorio, ProvinciaRepositorio provinciaRepositorio) {
        this.ciudadRepositorio = ciudadRepositorio;
        this.regionRepositorio = regionRepositorio;
        this.tipoIncidenciaRepositorio = tipoIncidenciaRepositorio;
        this.provinciaRepositorio = provinciaRepositorio;
    }

    // Método de mapeo no estático
    public static IncidenciaPrivateDTO toIncidenciaDTO(Incidencia incidencia) {
        if (incidencia == null) {
            return null;
        }

        // Obtener el nombre de la ciudad usando el ID de la ciudad
        String nombreCiudad = "";
        String nombreProvincia = "";
        if (incidencia.getCiudad() != null) {
            Ciudad ciudad = ciudadRepositorio.findById(incidencia.getCiudad().getId()).orElse(null);
            if (ciudad != null) {
                nombreCiudad = ciudad.getNombre();
                nombreProvincia = ciudad.getProvincia().getNombre();
            }
        }

        // Obtener el nombre de la región usando el ID de la región
        String nombreRegion = "";
        if (incidencia.getRegion() != null) {
            Region region = regionRepositorio.findById(incidencia.getRegion().getIdRegion()).orElse(null);
            if (region != null) {
                nombreRegion = region.getNombreEs();
            }
        }

        // Obtener el nombre del tipo de incidencia usando el ID del tipo de incidencia
        String nombreTipoIncidencia = "";
        if (incidencia.getTipoIncidencia() != null) {
            TipoIncidencia tipoIncidencia = tipoIncidenciaRepositorio.findById(incidencia.getTipoIncidencia().getId())
                    .orElse(null);
            if (tipoIncidencia != null) {
                nombreTipoIncidencia = tipoIncidencia.getNombre();
            }
        }

        // Crear el DTO con los datos obtenidos
        return new IncidenciaPrivateDTO(
                incidencia.getId(),
                incidencia.getLatitud(),
                incidencia.getLongitud(),
                incidencia.getCausa(),
                incidencia.getNivelIncidencia(),
                incidencia.getCarretera(),
                incidencia.getFechaInicio(),
                nombreCiudad,
                nombreProvincia,
                nombreRegion,
                nombreTipoIncidencia);
    }

    // Método estático (si es necesario)
    public static Incidencia toEntity(IncidenciaPrivateDTO incidenciaDTO) {
        Incidencia incidencia = new Incidencia();
        incidencia.setLatitud(incidenciaDTO.getLatitud());
        incidencia.setLongitud(incidenciaDTO.getLongitud());
        incidencia.setCausa(incidenciaDTO.getCausa());
        incidencia.setNivelIncidencia(incidenciaDTO.getNivelIncidencia());
        incidencia.setCarretera(incidenciaDTO.getCarretera());
        incidencia.setFechaInicio(incidenciaDTO.getFechaInicio());
        return incidencia;
    }
}
