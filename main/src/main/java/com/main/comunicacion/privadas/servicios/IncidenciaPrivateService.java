package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.IncidenciaPrivateMapper;
import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Incidencia;
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

    public List<IncidenciaPrivateDTO> obtenerIncidencias() {
        return incidenciaRepositorio.findAll()
            .stream()
            .map(IncidenciaPrivateMapper::toDTO)
            .collect(Collectors.toList());
    }
    
    public String crearIncidencia(IncidenciaPrivateDTO incidenciaDTO) {
        Incidencia incidencia = IncidenciaPrivateMapper.toEntity(incidenciaDTO);

        setEntidadesRelacionadas(incidencia, incidenciaDTO);

        incidenciaRepositorio.save(incidencia);

        return "Incidencia creada con ID: " + incidencia.getId();
    }

    public String actualizarIncidencia(IncidenciaPrivateDTO incidenciaDTO) {
        Incidencia incidencia = incidenciaRepositorio.findById(incidenciaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada."));

        IncidenciaPrivateMapper.updateEntityFromDTO(incidenciaDTO, incidencia);

        setEntidadesRelacionadas(incidencia, incidenciaDTO);

        incidenciaRepositorio.save(incidencia);

        return "Incidencia actualizada con ID: " + incidencia.getId();
    }

    public String eliminarIncidencia(Long id) {
        Incidencia incidencia = incidenciaRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada."));
        incidenciaRepositorio.delete(incidencia);
        return "Incidencia eliminada con éxito.";
    }

    private void setEntidadesRelacionadas(Incidencia incidencia, IncidenciaPrivateDTO incidenciaDTO) {
        incidencia.setCiudad(ciudadRepositorio.findById(incidenciaDTO.getIdCiudad())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada.")));

        incidencia.setTipoIncidencia(tipoIncidenciaRepositorio.findById(incidenciaDTO.getIdTipoIncidencia())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de incidencia no encontrado.")));

        incidencia.setRegion(regionRepositorio.findById(incidenciaDTO.getIdRegion())
                .orElseThrow(() -> new EntityNotFoundException("Región no encontrada.")));
    }

    
}
