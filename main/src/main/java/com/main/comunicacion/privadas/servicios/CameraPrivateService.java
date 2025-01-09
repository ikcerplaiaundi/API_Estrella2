package com.main.comunicacion.privadas.servicios;

import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;
import com.main.comunicacion.privadas.DTOs.CameraDTO;
import com.main.comunicacion.mapeos.CameraPrivateMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CameraPrivateService {

    private final CameraRepository cameraRepositorio;

    public CameraPrivateService(CameraRepository cameraRepositorio) {
        this.cameraRepositorio = cameraRepositorio;
    }

    // Obtener cámara por ID
    public CameraDTO obtenerCamaraPorId(Long id) {
        Camera camera = cameraRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cámara no encontrada"));
        return CameraPrivateMapper.toCameraDTO(camera);
    }

    // Obtener cámaras por región
    /*
    public List<CameraDTO> obtenerCamarasPorRegion(Long regionId) {
        List<Camera> cameras = cameraRepositorio.findRegion(regionId);
        return cameras.stream()
                .map(CameraPrivateMapper::toCameraDTO)
                .collect(Collectors.toList());
    } */

    // Obtener todas las cámaras
    public List<CameraDTO> obtenerCamaras() {
        List<Camera> cameras = cameraRepositorio.findAll();
        return cameras.stream()
                .map(CameraPrivateMapper::toCameraDTO)
                .collect(Collectors.toList());
    }
}