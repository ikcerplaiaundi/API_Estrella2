package com.main.comunicacion.openD.servicios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.main.comunicacion.openD.DTOs.CameraDTO;


public class CameraService {


    private static final String API_URL = "https://api.euskadi.eus/traffic/v1.0/cameras?_page=";


    @Autowired
    private RestTemplate restTemplate;


    // Método para obtener cámaras de una página específica
    public List<CameraDTO> fetchCamerasFromApi(int page) {
        String url = API_URL + page; // Construimos la URL con el número de página
        List<CameraDTO> cameraDTOList = null;


        try {
            // Usamos RestTemplate para hacer la solicitud GET
            ApiResponse.Response<CameraDTO> cameraResponse = restTemplate.getForObject(url, ApiResponse.Response.class);


            // Procesamos la respuesta si no es nula
            if (cameraResponse != null && cameraResponse.getData() != null) {
                cameraDTOList = cameraResponse.getData();
            } else {
                System.out.println("Error: La respuesta es nula o no contiene cámaras.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cameraDTOList;
    }

    // Metodo para obtener el numero total de páginas
    public int getTotalPages() {
        String url = API_URL + "1"; // Usamos la primera pagina para obtener información de la paginación
        int totalPages = 0;


        try {
            ApiResponse.Response<CameraDTO> cameraResponse = restTemplate.getForObject(url, ApiResponse.Response.class);


            if (cameraResponse != null) {
                totalPages = cameraResponse.getTotalPages();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPages;
    }
}
