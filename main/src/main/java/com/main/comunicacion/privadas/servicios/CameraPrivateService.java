package com.main.comunicacion.privadas.servicios;

import org.springframework.stereotype.Service;

import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;
import com.main.comunicacion.privadas.DTOs.CameraDTO;
import com.main.comunicacion.mapeos.CameraPrivateMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CameraPrivateService {

    private final CameraRepository cameraRepositorio;

    public CameraPrivateService(CameraRepository cameraRepositorio) {
        this.cameraRepositorio = cameraRepositorio;
    }

    public List<CameraDTO> obtenerCamaras() {
        List<Camera> cameras = cameraRepositorio.findAll();

        // Mapear las cámaras a CameraDTO y devolver la lista
        return cameras.stream()
                .map(CameraPrivateMapper::toCameraDTO)
                .collect(Collectors.toList());
    }
}
