package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.IncidenciaPrivateMapper;
import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.repositorios.IncidenciaRepositorio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.websocket.EncodeException;

@Service
public class IncidenciaPrivateService {

    private final IncidenciaRepositorio incidenciaRepositorio;

    public IncidenciaPrivateService(IncidenciaRepositorio incidenciaRepositorio) {
        this.incidenciaRepositorio = incidenciaRepositorio;
    }

    public List<IncidenciaPrivateDTO> obtenerIncidencias() {
        List<Incidencia> incidencias = incidenciaRepositorio.findAll();
        return incidencias.stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    }

    public Incidencia crearIncidencia(IncidenciaPrivateDTO incidenciaPrivateDTO) {

        Incidencia incidencia = IncidenciaPrivateMapper.toEntity(incidenciaPrivateDTO);
        return incidenciaRepositorio.save(incidencia);
    }

    public String actualizarIncidencia(IncidenciaPrivateDTO incidenciaPrivateDTO) {

        // Comprueba si la incidencia existe
        Optional<Incidencia> incidenciaExistente = incidenciaRepositorio.findById(incidenciaPrivateDTO.getId());

        // Si existe consegiremp
        // Consigue la incidencia antigua y sustituye solo lo que le pasamos
        if (incidenciaExistente.isPresent()) {

            // Conseguir objeto opcional
            Incidencia incidencia = incidenciaExistente.get();

            incidencia.setLatitud(incidenciaPrivateDTO.getLatitud());

            incidencia.setLongitud(incidenciaPrivateDTO.getLongitud());

            incidencia.setCausa(incidenciaPrivateDTO.getCausa());

            incidencia.setNivelIncidencia(incidenciaPrivateDTO.getNivelIncidencia());

            incidencia.setCarretera(incidenciaPrivateDTO.getCarretera());

            incidencia.setFechaInicio(incidenciaPrivateDTO.getFechaInicio());

            incidenciaRepositorio.save(incidencia);

            return "Incidencia con ID " + incidencia.getId() + " actualizada correctamente.";
        } else {
            throw new EntityNotFoundException("Incidencia con ID " + incidenciaPrivateDTO.getId() + " no encontrada.");
        }
    }

    public String  eliminarIncidencia(IncidenciaPrivateDTO incidenciaPrivateDTO) {

        // Comprueba si la incidencia existe
        Optional<Incidencia> incidenciaExistente = incidenciaRepositorio.findById(incidenciaPrivateDTO.getId());

        // Si existe consegiremp
        // Consigue la incidencia antigua y sustituye solo lo que le pasamos
        if (incidenciaExistente.isPresent()) {

            // Conseguir objeto opcional
            Incidencia incidencia = incidenciaExistente.get();

            incidenciaRepositorio.delete(incidencia);
            return "Incidencia con ID " + incidencia.getId() + " eliminada correctamente.";
        } else {
            throw new EntityNotFoundException("Incidencia con ID " + incidenciaPrivateDTO.getId() + " no encontrada.");
        }
    }
}