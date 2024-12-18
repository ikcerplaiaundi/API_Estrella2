package com.main.comunicacion.openD.Schedulers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.main.comunicacion.mapeos.CameraMap;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.comunicacion.openD.servicios.CameraService;
import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;

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
        int totalPages = cameraService.getTotalPages(); // Obtenemos el total de páginas desde el servicio
        List<Camera> savedCameras = new ArrayList<>();
        List<CameraDTO> cameraDTOList;

        // Iteramos a través de todas las páginas
        for (int page = 1; page <= totalPages; page++) {
            // Recuperamos las cámaras de la página actual
            cameraDTOList = cameraService.fetchCamerasFromApi(page);
            
            // Mapeamos y guardamos cada cámara
            for (CameraDTO dto : cameraDTOList) {
                Camera camera = cameraMapper.toEntity(dto);
                savedCameras.add(cameraRepository.save(camera)); // Guardamos en la base de datos
            }
        }

        System.out.println("Cámaras actualizadas y guardadas: " + savedCameras.size());
    }
}
