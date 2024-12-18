package com.main.comunicacion.openD;

import com.main.comunicacion.mapeos.CameraMap;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.comunicacion.openD.servicios.ApiResponse;
import com.main.comunicacion.openD.servicios.CameraService;
import com.main.main.SSLUtils;
import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CameraScheduler {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraMap cameraMapper;

    // Usamos @Scheduled para ejecutar este método cada 30 minutos
    @Scheduled(fixedRate = 1800000) // 30 minutos en milisegundos
    public void fetchAndSaveCameras() {
        executeCameraUpdate();
    }

    // Ejecutar el método al inicio de la aplicación
    @PostConstruct
    public void initializeFetchAndSaveCameras() {
        System.out.println("Ejecutando actualización inicial de cámaras...");
        SSLUtils.disableSslVerification();
        executeCameraUpdate();
    }

    // Lógica común para la actualización de cámaras
    private void executeCameraUpdate() {
        List<Camera> savedCameras = new ArrayList<>();
        int currentPage = 1;
        ApiResponse<CameraDTO> response;

        do {
            response = cameraService.fetchCamerasFromApiResponse(currentPage);

            // Verificar si la respuesta es válida
            if (response == null || response.getData() == null || response.getData().isEmpty()) {
                System.out.println("Error: No se pudo obtener datos de la API en la página " + currentPage);
                break;
            }

            List<CameraDTO> cameraDTOList = response.getData();

            for (CameraDTO dto : cameraDTOList) {
                Camera camera = cameraMapper.toEntity(dto);
                savedCameras.add(cameraRepository.save(camera));
            }

            currentPage = response.getCurrentPage() + 1;
        } while (currentPage <= response.getTotalPages());

        System.out.println("Cámaras actualizadas y guardadas: " + savedCameras.size());
    }
}