package com.main.comunicacion.openD;

import com.main.comunicacion.mapeos.CameraMap;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.comunicacion.openD.servicios.ApiResponse;
import com.main.comunicacion.openD.servicios.CameraService;
import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;

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

    // Usamos @Scheduled para ejecutar este método cada 30 minutos (ajustar según necesidad)
    @Scheduled(fixedRate = 1800000) // 30 minutos en milisegundos
    public void fetchAndSaveCameras() {
        List<Camera> savedCameras = new ArrayList<>();
        int currentPage = 1; // Iniciamos en la primera página
        ApiResponse.Response<CameraDTO> response;

        do {
            // Llamamos al servicio para obtener la respuesta paginada
            response = cameraService.fetchCamerasFromApiResponse(currentPage);

            // Validamos que la respuesta no sea nula
            if (response == null || response.getData() == null) {
                System.out.println("Error: No se pudo obtener datos de la API en la página " + currentPage);
                break;
            }

            // Obtenemos la lista de datos de la respuesta
            List<CameraDTO> cameraDTOList = response.getData();

            // Iteramos sobre los DTOs y guardamos las entidades
            for (CameraDTO dto : cameraDTOList) {
                Camera camera = cameraMapper.toEntity(dto);
                savedCameras.add(cameraRepository.save(camera)); // Guardamos en la base de datos
            }

            // Avanzamos a la siguiente página
            currentPage = response.getCurrentPage() + 1;

        } while (currentPage <= response.getTotalPages()); // Continuamos hasta la última página

        System.out.println("Cámaras actualizadas y guardadas: " + savedCameras.size());
    }
}
