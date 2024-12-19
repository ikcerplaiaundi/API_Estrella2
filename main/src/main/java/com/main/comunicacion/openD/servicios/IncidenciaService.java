package com.main.comunicacion.openD.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.support.StandardServletPartUtils;

import com.main.comunicacion.mapeos.IncidenciaMap;
import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.repositorios.IncidenciaRepositorio;

public class IncidenciaService {

    private String ano = "2023";
    private String mes = "1";

   private static final String URL_ANO = "https://api.euskadi.eus/traffic/v1.0/incidences/byYear/${ano}?_pagina=";

   @Autowired
   private IncidenciaRepositorio incidenciaRepositorio;


    @Autowired
    private RestTemplate restTemplate;

    

    public void peticionIncidenciasDeLaAPIMes() {
        System.out.println("En guardado");
        String baseUrl = "https://api.euskadi.eus/traffic/v1.0/incidences/byMonth/${ano}/${mes}?_pagina=";
        
        List<IncidenciaDTO> incidenciasDTO = new ArrayList<>();

        try {
            
            String urlPrimeraPagina = baseUrl.replace("${pagina}", "1");
            IncidenciaResponse response = restTemplate.getForObject(urlPrimeraPagina, IncidenciaResponse.class);
            int paginasTotales = response.getTotalPages();

            
            for (int pagina = 1; pagina <= paginasTotales; pagina++) {
                String url = baseUrl.replace("${pagina}", String.valueOf(pagina));
                IncidenciaResponse responsePagina = restTemplate.getForObject(url, IncidenciaResponse.class);

                if (responsePagina != null && responsePagina.getincidencias() != null) {
                    
                    incidenciasDTO.addAll(responsePagina.getincidencias());
                }
            }
            IncidenciaMap incidenciaMap = new IncidenciaMap();
            for (IncidenciaDTO incidenciaDTO : incidenciasDTO) {

                Incidencia incidencia = new Incidencia();

                incidencia = IncidenciaMap.toEntity(incidenciaDTO);

                incidenciaRepositorio.save(incidencia);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
    }

    // public List<IncidenciaDTO> peticionIncidenciasDeLaAPIAno(int pagina) {
    //     String url = URL_ANO + pagina; 
    //     List<IncidenciaDTO> listadoIncidencias = null;


    //     try {
            
    //         IncidenciaResponse<IncidenciaDTO> respuestaIncidencias = restTemplate.getForObject(url, IncidenciaResponse.class);

    //         if (respuestaIncidencias != null && respuestaIncidencias.getincidencias() != null) {
    //             listadoIncidencias = respuestaIncidencias.getincidencias();
    //         } else {
    //             System.out.println("Error: La respuesta es nula o no contiene cámaras.");
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return listadoIncidencias;
    // }

    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        peticionIncidenciasDeLaAPIMes();

    }

}
