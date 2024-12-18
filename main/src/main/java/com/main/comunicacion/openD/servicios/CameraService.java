package com.main.comunicacion.openD.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.main.comunicacion.openD.DTOs.CameraDTO;

@Service
public class CameraService {

    private static final String API_URL = "https://api.euskadi.eus/traffic/v1.0/cameras?_page=";

    @Autowired
    private RestTemplate restTemplate;

    // Metodo para obtener cámaras y datos de paginación en una página específica
    public ApiResponse<CameraDTO> fetchCamerasFromApiResponse(int currentPage) {
        System.out.println("\n\nMétodo para obtener cámaras y datos de paginación en una página específica\n\n");
        String url = API_URL + currentPage; // Construimos la URL con el número de página

        ApiResponse<CameraDTO> apiResponse = null;

        try {
            // Usamos RestTemplate para hacer la solicitud GET y mapear directamente a la clase ApiResponse
            apiResponse = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<CameraDTO>>() {}
            ).getBody();

            // Verificación básica para detectar errores
            if (apiResponse == null || apiResponse.getData() == null) {
                System.out.println("Error: La respuesta de la API es nula o no contiene datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }
}