package com.main.comunicacion.mapeos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTOCRUD;
import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.entidades.Provincia;
import com.main.modelo.entidades.Region;
import com.main.modelo.entidades.TipoIncidencia;
import com.main.modelo.repositorios.CiudadRepositorio;
import com.main.modelo.repositorios.ProvinciaRepositorio;
import com.main.modelo.repositorios.RegionRepository;
import com.main.modelo.repositorios.TipoIncidenciaRepositorio;

//Mapeos de incidencia de solicitudes internas API
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

        // Obtener la ciudad y provincia de forma segura
        Ciudad ciudad = null;
        Provincia provincia = null;
        if (incidencia.getCiudad() != null) {
            ciudad = ciudadRepositorio.findById(incidencia.getCiudad().getId()).orElse(null);
            if (ciudad != null) {
                provincia = ciudad.getProvincia();
            }
        }

        // Obtener la región de forma segura
        Region region = null;
        if (incidencia.getRegion() != null) {
            region = regionRepositorio.findById(incidencia.getRegion().getIdRegion()).orElse(null);
        }

        // Obtener el tipo de incidencia de forma segura
        TipoIncidencia tipoIncidencia = null;
        if (incidencia.getTipoIncidencia() != null) {
            tipoIncidencia = tipoIncidenciaRepositorio.findById(incidencia.getTipoIncidencia().getId()).orElse(null);
        }

        // Crear el DTO con datos seguros
        return new IncidenciaPrivateDTO(
                incidencia.getId(),
                incidencia.getLatitud(),
                incidencia.getLongitud(),
                incidencia.getCausa(),
                incidencia.getNivelIncidencia(),
                incidencia.getCarretera(),
                incidencia.getFechaInicio(),
                ciudad,
                provincia,
                region,
                tipoIncidencia
        );
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

    public static Incidencia toEntityCRUD(IncidenciaPrivateDTOCRUD incidenciaDTO) {
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
