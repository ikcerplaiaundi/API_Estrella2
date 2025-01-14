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

//Servicios que ofrecen todas las solicitudes de Incidencia a la api interna
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
        List<Incidencia> incidencias = incidenciaRepositorio.findAll();
        return incidencias.stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    }

    public List<IncidenciaPrivateDTO> obtenerIncidenciasCiudad(Long idCiudad) {
        return incidenciaRepositorio.findByCiudad_Id(idCiudad).stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    }

    public List<IncidenciaPrivateDTO> obtenerIncidenciasProvincia(Long idProvincia) {
        return incidenciaRepositorio.findByProvinciaId(idProvincia).stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    }

    public List<IncidenciaPrivateDTO> obtenerIncidenciasRegion(Long idRegion) {
        return incidenciaRepositorio.findByRegion_Id(idRegion).stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    }

    public List<IncidenciaPrivateDTO> obtenerIncidenciasTipoIncidencia(Long idIncidencia) {
        return incidenciaRepositorio.findByTipoIncidencia_Id(idIncidencia).stream()
                .map(IncidenciaPrivateMapper::toIncidenciaDTO)
                .collect(Collectors.toList());
    }
    public String crearIncidencia(IncidenciaPrivateDTO incidenciaPrivateDTO) {

        Incidencia incidencia = IncidenciaPrivateMapper.toEntity(incidenciaPrivateDTO);

        // Comprueba si la incidencia existe
        Optional<Ciudad> provinciaExistente = ciudadRepositorio.findById(incidenciaPrivateDTO.getIdCiudad());
        Optional<TipoIncidencia> tipoIncidenciaExistente = tipoIncidenciaRepositorio.findById(incidenciaPrivateDTO.getIdTipoIncidencia());
        Optional<Region> regionExistente = regionRepositorio.findById(incidenciaPrivateDTO.getIdRegion());
    

        if (provinciaExistente.isPresent() && tipoIncidenciaExistente.isPresent()&& regionExistente.isPresent()) {

            incidencia.setCiudad(provinciaExistente.get());
            incidencia.setTipoIncidencia(tipoIncidenciaExistente.get());
            incidencia.setRegion(regionExistente.get());

        }

        incidenciaRepositorio.save(incidencia);

        return "Incidencia con ID " + incidencia.getId() + " creada correctamente.";
    
    }

    public String actualizarIncidencia(IncidenciaPrivateDTO incidenciaPrivateDTO) {

        // Comprueba si la incidencia existe
        Optional<Incidencia> incidenciaExistente = incidenciaRepositorio.findById(incidenciaPrivateDTO.getId());
        Optional<Ciudad> provinciaExistente = ciudadRepositorio.findById(incidenciaPrivateDTO.getIdCiudad());
        Optional<TipoIncidencia> tipoIncidenciaExistente = tipoIncidenciaRepositorio.findById(incidenciaPrivateDTO.getIdTipoIncidencia());
        Optional<Region> regionExistente = regionRepositorio.findById(incidenciaPrivateDTO.getIdRegion());
    

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

         
    
            if (provinciaExistente.isPresent() && tipoIncidenciaExistente.isPresent()&& regionExistente.isPresent()) {
    
                incidencia.setCiudad(provinciaExistente.get());
                incidencia.setTipoIncidencia(tipoIncidenciaExistente.get());
                incidencia.setRegion(regionExistente.get());
    
            }

            incidenciaRepositorio.save(incidencia);

            return "Incidencia con ID " + incidencia.getId() + " actualizada correctamente.";
        } else {
            throw new EntityNotFoundException("Incidencia con ID " + incidenciaPrivateDTO.getId() + " no encontrada.");
        }
    }

    public String eliminarIncidencia(IncidenciaPrivateDTO incidenciaPrivateDTO) {

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
