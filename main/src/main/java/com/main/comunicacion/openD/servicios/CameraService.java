package com.main.comunicacion.openD.servicios;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.comunicacion.mapeos.CameraMap;
import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.modelo.entidades.Camera;
import com.main.modelo.repositorios.CameraRepository;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CameraService {

    private static final String API_URL = "https://api.euskadi.eus/traffic/v1.0/cameras";

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraMap cameraMapper;

    public List<Camera> fetchAndSaveCameras() {
        List<CameraDTO> cameraDTOList = fetchCamerasFromApi();
        List<Camera> savedCameras = new ArrayList<>();
        for (CameraDTO dto : cameraDTOList) {
            Camera camera = cameraMapper.toEntity(dto);
            savedCameras.add(cameraRepository.save(camera));
        }
        return savedCameras;
    }

    private List<CameraDTO> fetchCamerasFromApi() {
        List<CameraDTO> cameraDTOList = new ArrayList<>();
    
        try {
            // Configurar la conexión
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(10000); // Tiempo de espera para establecer la conexión
            connection.setReadTimeout(10000); // Tiempo de espera para leer la respuesta
    
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) { // HTTP OK
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
    
                // Procesar el JSON de respuesta
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.toString());
                JsonNode camerasNode = rootNode.path("cameras");
    
                for (JsonNode cameraNode : camerasNode) {
                    CameraDTO dto = new CameraDTO();
                    dto.setCameraId(cameraNode.path("cameraId").asText());
                    dto.setSourceId(cameraNode.path("sourceId").asText());
                    dto.setCameraName(cameraNode.path("cameraName").asText());
                    dto.setLatitude(cameraNode.path("latitude").asText());
                    dto.setLongitude(cameraNode.path("longitude").asText());
                    dto.setRoad(cameraNode.path("road").asText());
                    dto.setKilometer(cameraNode.path("kilometer").asText());
                    dto.setAddress(cameraNode.path("address").asText());
                    dto.setUrlImage(cameraNode.path("urlImage").asText(null)); // Campo opcional
    
                    cameraDTOList.add(dto);
                }
            } else {
                System.out.println("Error al obtener datos: Código de respuesta " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return cameraDTOList;
    }
}