package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.IncidenciaPrivateMapper;
import com.main.comunicacion.privadas.DTOs.IncidenciaActualizarDTO;
import com.main.comunicacion.privadas.DTOs.IncidenciaConReferenciasDTO;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Region;
import com.main.modelo.entidades.TipoIncidencia;

import com.main.modelo.repositorios.CiudadRepositorio;
import com.main.modelo.repositorios.IncidenciaRepositorio;
import com.main.modelo.repositorios.RegionRepository;
import com.main.modelo.repositorios.TipoIncidenciaRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IncidenciaPrivateService {

    @Autowired
    private IncidenciaRepositorio incidenciaRepositorio;

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    @Autowired
    private TipoIncidenciaRepositorio tipoIncidenciaRepositorio;

    @Autowired
    private RegionRepository regionRepositorio;

    // Obtener incidencias con todas las referencias (ciudad, tipoIncidencia, region)
    public List<IncidenciaConReferenciasDTO> obtenerIncidenciasConReferencias() {
        return incidenciaRepositorio.findAll()
            .stream()
            .map(IncidenciaPrivateMapper::toDTOConReferencias)
            .collect(Collectors.toList());
    }

    // Crear una incidencia
    public String crearIncidencia(IncidenciaActualizarDTO incidenciaDTO) {
        Incidencia incidencia = IncidenciaPrivateMapper.toEntity(incidenciaDTO);
        setEntidadesRelacionadas(incidencia, incidenciaDTO);
        incidenciaRepositorio.save(incidencia);
        return "Incidencia creada con ID: " + incidencia.getId();
    }

    // Actualizar una incidencia
    public String actualizarIncidencia(IncidenciaActualizarDTO incidenciaDTO) {
        Incidencia incidencia = incidenciaRepositorio.findById(incidenciaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada."));
        IncidenciaPrivateMapper.updateEntityFromDTO(incidenciaDTO, incidencia);
        setEntidadesRelacionadas(incidencia, incidenciaDTO);
        incidenciaRepositorio.save(incidencia);
        return "Incidencia actualizada con ID: " + incidencia.getId();
    }

    // Eliminar una incidencia
    public String eliminarIncidencia(Long id) {
        Incidencia incidencia = incidenciaRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada."));
        incidenciaRepositorio.delete(incidencia);
        return "Incidencia eliminada con éxito.";
    }

    private void setEntidadesRelacionadas(Incidencia incidencia, IncidenciaActualizarDTO incidenciaDTO) {
        Ciudad ciudad = ciudadRepositorio.findById(incidenciaDTO.getIdCiudad())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada."));
        incidencia.setCiudad(ciudad);

        TipoIncidencia tipoIncidencia = tipoIncidenciaRepositorio.findById(incidenciaDTO.getIdTipoIncidencia())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de incidencia no encontrado."));
        incidencia.setTipoIncidencia(tipoIncidencia);

        Region region = regionRepositorio.findById(incidenciaDTO.getIdRegion())
                .orElseThrow(() -> new EntityNotFoundException("Región no encontrada."));
        incidencia.setRegion(region);
    }
}
