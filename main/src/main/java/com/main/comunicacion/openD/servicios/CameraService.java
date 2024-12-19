package com.main.comunicacion.openD.servicios;


import com.main.comunicacion.mapeos.CameraMap;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CameraService {


    private static final String API_URL = "https://api.euskadi.eus/traffic/v1.0/cameras?_page=";


    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraMap cameraMapper;

    // Método para obtener y guardar todas las cámaras desde la API
    public void fetchAndSaveAllCameras() {
        int currentPage = 1;
        ApiResponse apiResponse;
        do {
            apiResponse = fetchCamerasFromApiResponse(currentPage);
            if (apiResponse == null || apiResponse.getCameras() == null) {
                break;

            }

            // Mapea y guarda las cámaras obtenidas
            apiResponse.getCameras().forEach(dto -> {
                Camera camera = cameraMapper.toEntity(dto);
                cameraRepository.save(camera); // Guardar camara en la base de datos
            });

            currentPage = apiResponse.getCurrentPage() + 1; // Ir a la siguiente página
        } while (currentPage <= apiResponse.getTotalPages());
    }


    // Método para obtener la respuesta de la API usando getForObject
    private ApiResponse fetchCamerasFromApiResponse(int currentPage) {
        String url = API_URL + currentPage;
        try {
            // Usamos getForObject para obtener directamente la respuesta
            return restTemplate.getForObject(url, ApiResponse.class);

        } catch (Exception e) {
            System.out.println("Error durante la solicitud a la API: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    // Subclase ApiResponse dentro del servicio para mayor encapsulamiento
    @Data
    private static class ApiResponse {
        private int totalItems;
        private int totalPages;
        private int currentPage;
        private List<CameraDTO> cameras;
    }

}
