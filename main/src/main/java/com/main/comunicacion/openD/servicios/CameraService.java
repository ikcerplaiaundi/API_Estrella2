package com.main.comunicacion.openD.servicios;

import java.util.List;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.ProjCoordinate;
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
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem utmCRS = crsFactory.createFromName("EPSG:25830"); // UTM Zone 30N
        CoordinateReferenceSystem wgs84CRS = crsFactory.createFromName("EPSG:4326"); // WGS84 (GPS)

        do {
            apiResponse = fetchCamerasFromApiResponse(currentPage);

            if (apiResponse == null || apiResponse.getCameras() == null) {
                break;
            }

            // Mapea y guarda las camaras obtenidas
            apiResponse.getCameras().forEach(dto -> {
                try {
                    Camera camera = cameraMapper.toEntity(dto);

                    if (camera.getLatitud() != null && camera.getLongitud() != null) {
                        try {
                            double lat = Double.parseDouble(camera.getLatitud());
                            double lon = Double.parseDouble(camera.getLongitud());

                            // Si las coordenadas estan fuera del rango esperado para GPS, se asume que son
                            // UTM
                            if (lat > 90 || lon > 180) {
                                ProjCoordinate utmCoord = new ProjCoordinate(lon, lat); // UTM usa (x, y)
                                ProjCoordinate gpsCoord = new ProjCoordinate();

                                // Transformar UTM a WGS84
                                new org.locationtech.proj4j.BasicCoordinateTransform(utmCRS, wgs84CRS)
                                        .transform(utmCoord, gpsCoord);

                                // Asignar las coordenadas GPS a la camara
                                camera.setLatitud(String.valueOf(gpsCoord.y)); // Latitud
                                camera.setLongitud(String.valueOf(gpsCoord.x)); // Longitud
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Coordenadas inválidas para la cámara con ID: " + dto.getId());
                        }
                    }
                    // Buscar la región asociada
                    Region regionAsociada = getRegionForCamera(dto);
                    
                    camera.setRegion(regionAsociada); // Establecer la relación de la camara con la región

                    cameraRepository.save(camera); // Guardar la camara en la base de datos
                } catch (Exception e) {
                    System.err.println("Error al procesar la cámara con ID: " + dto.getId() + ". " + e.getMessage());
                    e.printStackTrace();
                }
            });

            currentPage = apiResponse.getCurrentPage() + 1; // Ir a la siguiente pagina
        } while (currentPage <= apiResponse.getTotalPages());
    }

    // Obtener la región asociada a la cámara
    private Region getRegionForCamera(CameraDTO dto) {
        List<Region> regionList = regionRepository.findByIdRegion(dto.getSourceId());

        if (!regionList.isEmpty()) {
            return regionList.get(0); // Si la region existe, la retornamos

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
