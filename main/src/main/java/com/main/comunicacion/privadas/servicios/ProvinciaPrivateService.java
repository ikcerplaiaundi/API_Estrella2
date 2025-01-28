package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.IncidenciaPrivateMapper;
import com.main.comunicacion.mapeos.ProvinciaPrivateMapper;
import com.main.comunicacion.privadas.DTOs.IncidenciaPrivateDTO;
import com.main.comunicacion.privadas.DTOs.ProvinciaPrivateDTO;
import com.main.modelo.entidades.Provincia;
import com.main.modelo.repositorios.ProvinciaRepositorio;

//Servicios que ofrecen todas las solicitudes de provincia a la api interna
@Service
public class ProvinciaPrivateService {

    private final ProvinciaRepositorio provinciaRepositorio;

    public ProvinciaPrivateService(ProvinciaRepositorio provinciaRepositorio) {
        this.provinciaRepositorio = provinciaRepositorio;
    }

      //Servicio con el cual obtendremos todas las provincias
    public List<ProvinciaPrivateDTO> obtenerProvincias() {
        List<Provincia> provincias = provinciaRepositorio.findAll();
        return provincias.stream()
                .map(ProvinciaPrivateMapper::toProvinciaDTO)
                .collect(Collectors.toList());
    }

    //Servicio con el cual obtendremos una provincia
    public List<ProvinciaPrivateDTO> obtenerProvincia(Long id) {
        return provinciaRepositorio.findById(id).stream()
                .map(ProvinciaPrivateMapper::toProvinciaDTO)
                .collect(Collectors.toList());
    }
}
