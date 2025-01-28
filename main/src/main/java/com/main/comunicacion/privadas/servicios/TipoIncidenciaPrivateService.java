package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.TipoIncidenciaPrivateMapper;
import com.main.comunicacion.privadas.DTOs.TipoIncidenciaPrivateDTO;
import com.main.modelo.entidades.TipoIncidencia;
import com.main.modelo.repositorios.TipoIncidenciaRepositorio;

//Servicios que ofrecen todas las solicitudes de tipoIncidencia a la api interna
@Service
public class TipoIncidenciaPrivateService {

    private final TipoIncidenciaRepositorio incidenciaRepositorio;

    public TipoIncidenciaPrivateService(TipoIncidenciaRepositorio incidenciaRepositorio) {
        this.incidenciaRepositorio = incidenciaRepositorio;
    }

    //Servicio con el cual obtendremos todos los tipos de incidencias
    public List<TipoIncidenciaPrivateDTO> obtenerTiposIncidencias() {
        List<TipoIncidencia> provincias = incidenciaRepositorio.findAll();
        return provincias.stream()
                .map(TipoIncidenciaPrivateMapper::toTipoIncidenciaDTO)
                .collect(Collectors.toList());
    }
}
