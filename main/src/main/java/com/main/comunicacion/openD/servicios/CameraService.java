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

    // Método para obtener cámaras y datos de paginación en una página específica
    public ApiResponse<CameraDTO> fetchCamerasFromApiResponse(int currentPage) {
        
        String url = API_URL + currentPage; // Construimos la URL con el número de página
        System.out.println("\n\nMétodo para obtener cámaras y datos de paginación en una página específica\n"+url+"\n\n" );
        ApiResponse<CameraDTO> apiResponse = null;

        try {
            // Usamos RestTemplate para hacer la solicitud GET y mapear directamente a la clase ApiResponse
            apiResponse = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<CameraDTO>>() {}
            ).getBody();

            // Imprimir la respuesta completa por consola
            System.out.println("Respuesta completa de la API: " + apiResponse);
            // Verificación básica para detectar errores

            if (apiResponse.getTotalItems()== 0){
                System.out.println("Error total item 0 ");
            }
            // Verificar si la respuesta es nula
            if (apiResponse == null) {
                System.out.println("Error: La respuesta de la API es nula.");
            } else {
                // Verificar si los datos son nulos
                if (apiResponse.getCameras() == null) {
                    System.out.println("Error: La respuesta de la API no contiene datos.");
                } else {
                    // Imprimir los datos recibidos
                    System.out.println("Datos de la respuesta: " + apiResponse.getCameras());
                }
            } 
        } catch (Exception e) {
            System.out.println("Error durante la solicitud a la API: " + e.getMessage());
            e.printStackTrace();
        }
        return apiResponse;
    }
}
