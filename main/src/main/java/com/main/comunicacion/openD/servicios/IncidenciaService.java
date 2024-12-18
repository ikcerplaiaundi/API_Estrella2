package com.main.comunicacion.openD.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.main.comunicacion.openD.DTOs.IncidenciaDTO;

public class IncidenciaService {

    private String ano = "2023";
    private String mes = "1";

    private static final String URL_MES = "https://api.euskadi.eus/traffic/v1.0/incidences/byMonth/${ano}/${mes}?_pagina=";
    private static final String URL_ANO = "https://api.euskadi.eus/traffic/v1.0/incidences/byYear/${ano}?_pagina=";




    @Autowired
    private RestTemplate restTemplate;


    
    public List<IncidenciaDTO> peticionIncidenciasDeLaAPIMes(int pagina) {
        String url = URL_MES + pagina; 
        List<IncidenciaDTO> listadoIncidencias = null;


        try {
            
            ApiResponse.Response<IncidenciaDTO> respuestaIncidencias = restTemplate.getForObject(url, ApiResponse.Response.class);


            // Procesamos la respuesta si no es nula
            if (respuestaIncidencias != null && respuestaIncidencias.getData() != null) {
                listadoIncidencias = respuestaIncidencias.getData();
            } else {
                System.out.println("Error: La respuesta es nula o no contiene cámaras.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listadoIncidencias;
    }

    public List<IncidenciaDTO> peticionIncidenciasDeLaAPIAno(int pagina) {
        String url = URL_ANO + pagina; 
        List<IncidenciaDTO> listadoIncidencias = null;


        try {
            
            ApiResponse.Response<IncidenciaDTO> respuestaIncidencias = restTemplate.getForObject(url, ApiResponse.Response.class);

            if (respuestaIncidencias != null && respuestaIncidencias.getData() != null) {
                listadoIncidencias = respuestaIncidencias.getData();
            } else {
                System.out.println("Error: La respuesta es nula o no contiene cámaras.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listadoIncidencias;
    }

}
