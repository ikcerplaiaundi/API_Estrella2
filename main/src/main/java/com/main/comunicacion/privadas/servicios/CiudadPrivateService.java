package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.CiudadPrivateMapper;
import com.main.comunicacion.privadas.DTOs.CiudadPrivateDTO;
import com.main.modelo.entidades.Ciudad;
import com.main.modelo.repositorios.CiudadRepositorio;

//Servicios que ofrecen todas las solicitudes de ciudad a la api interna
@Service
public class CiudadPrivateService {

    private final CiudadRepositorio ciudadRepositorio;

    //Servicio con el cual obtendremos todas las ciudades
    public CiudadPrivateService(CiudadRepositorio ciudadRepositorio) {
        this.ciudadRepositorio = ciudadRepositorio;
    }

    public List<CiudadPrivateDTO> obtenerCiudades() {
        List<Ciudad> ciudades = ciudadRepositorio.findAll();
        return ciudades.stream()
                .map(CiudadPrivateMapper::toCiudadDTO)
                .collect(Collectors.toList());
    }
}
