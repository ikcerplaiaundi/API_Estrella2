package com.main.comunicacion.openD.servicios;

import com.main.comunicacion.openD.DTOs.CameraDTO;
import com.main.comunicacion.openD.servicios.ApiResponse.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class CameraService {

    private static final String API_URL = "https://api.euskadi.eus/traffic/v1.0/cameras?_page=";

    @Autowired
    private RestTemplate restTemplate;

    // Metodo para obtener camaras y datos de paginacion en una pagina especifica
    public Response<CameraDTO> fetchCamerasFromApiResponse(int currentPage) {
        String url = API_URL + currentPage; // Construimos la URL con el numero de pagina
        Response<CameraDTO> cameraResponse = null;

        try {
            // Usamos RestTemplate para hacer la solicitud GET y mapear directamente a la clase Response
            cameraResponse = restTemplate.getForObject(url, Response.class);

            // Verificacion basica para detectar errores
            if (cameraResponse == null || cameraResponse.getData() == null) {
                System.out.println("Error: La respuesta de la API es nula o no contiene datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cameraResponse;
    }
}