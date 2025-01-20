package com.main.comunicacion.privadas.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.comunicacion.mapeos.CameraPrivateMapper;
import com.main.comunicacion.privadas.DTOs.CameraPrivateDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Region;
import com.main.modelo.repositorios.CameraRepository;
import com.main.modelo.repositorios.RegionRepository;

//Servicios que ofrecen todas las solicitudes de camara a la api interna
@Service
public class CameraPrivateService {
    @Autowired
    private  CameraRepository cameraRepositorio;
    @Autowired
    private  RegionRepository regionRepositorio;

    

    // Obtener cámara por ID
    public CameraPrivateDTO obtenerCamaraPorId(Long id) {
        Camera camera = cameraRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cámara no encontrada"));
        return CameraPrivateMapper.toCameraDTO(camera);
    }

    // Obtener cámaras por región
    public List<CameraPrivateDTO> obtenerCamarasPorRegion(Long regionId) {
        List<Region> regiones = regionRepositorio.findByIdRegion(regionId);
        List<Camera> cameras = cameraRepositorio.findByRegion(regiones.get(0));
        return cameras.stream()
                .map(CameraPrivateMapper::toCameraDTO)
                .collect(Collectors.toList());
    } 

    // Obtener todas las cámaras
    public List<CameraPrivateDTO> obtenerCamaras() {
        List<Camera> cameras = cameraRepositorio.findAll();
        return cameras.stream()
                .map(CameraPrivateMapper::toCameraDTO)
                .collect(Collectors.toList());
    }

    
}