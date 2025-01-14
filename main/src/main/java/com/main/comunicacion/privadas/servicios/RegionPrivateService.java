package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.RegionPrivateMapper;
import com.main.comunicacion.privadas.DTOs.RegionPrivateDTO;
import com.main.modelo.entidades.Region;
import com.main.modelo.repositorios.RegionRepository;

//Servicios que ofrecen todas las solicitudes de region a la api interna
@Service
public class RegionPrivateService {
    @Autowired
    private RegionRepository regionRepositorio;

    // Obtener región por ID
    public RegionPrivateDTO obtenerRegionPorId(Long id) {
        Region region = regionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        return RegionPrivateMapper.toRegionDTO(region);
    }

    // Obtener todas las regiones
    public List<RegionPrivateDTO> obtenerRegiones() {
        List<Region> regiones = regionRepositorio.findAll();
        return regiones.stream()
                .map(RegionPrivateMapper::toRegionDTO)
                .collect(Collectors.toList());
    }
}
