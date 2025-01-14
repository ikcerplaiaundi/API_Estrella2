package com.main.comunicacion.openD.servicios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.main.comunicacion.mapeos.CameraMap;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Region;
import com.main.modelo.repositorios.CameraRepository;
import com.main.modelo.repositorios.RegionRepository;

import lombok.Data;


//Gestionará las peticiones a la cámara de open data
@Service
public class CameraService {


    private static final String API_URL = "https://api.euskadi.eus/traffic/v1.0/cameras?_page=";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraMap cameraMapper;

    @Autowired
    private RegionRepository regionRepository;

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
                try {
                    Camera camera = cameraMapper.toEntity(dto);

                    // Buscar la región asociada
                    Region regionAsociada = getRegionForCamera(dto);
                    camera.setRegion(regionAsociada); // Establecer la relación de la cámara con la región

                    cameraRepository.save(camera); // Guardar la cámara en la base de datos
                } catch (Exception e) {
                    System.err.println("Error al procesar la cámara con ID: " + dto.getId() + ". " + e.getMessage());
                    e.printStackTrace();
                }
            });

            currentPage = apiResponse.getCurrentPage() + 1; // Ir a la siguiente página
        } while (currentPage <= apiResponse.getTotalPages());
    }

    // Obtener la región asociada a la cámara
    private Region getRegionForCamera(CameraDTO dto) {
        List<Region> regionList = regionRepository.findByIdRegion(dto.getSourceId());

        if (!regionList.isEmpty()) {
            return regionList.get(0); // Si la región existe, la retornamos
            
        } else {
            // Manejo si no se encuentra la región
            System.err.println("Región no encontrada para idRegion: " + dto.getSourceId());
            return null;
        }
    }

    // Metodo para obtener la respuesta de la API usando getForObject
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
