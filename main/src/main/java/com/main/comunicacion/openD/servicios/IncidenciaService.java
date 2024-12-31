package com.main.comunicacion.openD.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.main.comunicacion.mapeos.IncidenciaMap;
import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.repositorios.IncidenciaRepositorio;
import com.main.comunicacion.openD.servicios.IncidenciaResponse;


@Service
public class IncidenciaService {

    public String ano = "2023";
    public String mes = "1";

    public static final String URL_ANO = "https://api.euskadi.eus/traffic/v1.0/incidences/byYear/${ano}?_pagina=";

    @Autowired
    public IncidenciaRepositorio incidenciaRepositorio;

    @Autowired
    public RestTemplate restTemplate;

    public void peticionIncidenciasDeLaAPIMes() {
        
       String baseUrl = "https://api.euskadi.eus/traffic/v1.0/incidences/byMonth/"+ano+"/"+mes+"?_page=${pagina}";
        List<IncidenciaDTO> incidenciasDTO = new ArrayList<>();

        try {

            
            String urlPrimeraPagina = baseUrl.replace("${pagina}", "1");
            
            IncidenciaResponse<IncidenciaDTO> response = restTemplate.getForObject(urlPrimeraPagina, IncidenciaResponse.class);
            int paginasTotales = response.getTotalPages();
            IncidenciaMap incidenciaMap = new IncidenciaMap();
            

            for (int pagina = 1; pagina <= 10; pagina++) {
                String url = baseUrl.replace("${pagina}", String.valueOf(pagina));
                System.out.println("url "+url);
                IncidenciaResponse<IncidenciaDTO>  responsePagina = restTemplate.getForObject(url, IncidenciaResponse.class);

                System.out.println("response "+responsePagina);
                // if (responsePagina != null && responsePagina.getIncidences() != null) {

                    
                //     for (IncidenciaDTO incidenciaDTO : responsePagina.getIncidences()) {

                //         System.out.println("Hola"+incidenciaDTO.getCityTown());
                //         Incidencia incidencia = new Incidencia();
        
                //         incidencia = IncidenciaMap.toEntity(incidenciaDTO);
        
                //         incidenciaRepositorio.save(incidencia);
                //     }
                // }
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // public List<IncidenciaDTO> peticionIncidenciasDeLaAPIAno(int pagina) {
    // String url = URL_ANO + pagina;
    // List<IncidenciaDTO> listadoIncidencias = null;

    // try {

    // IncidenciaResponse<IncidenciaDTO> respuestaIncidencias =
    // restTemplate.getForObject(url, IncidenciaResponse.class);

    // if (respuestaIncidencias != null && respuestaIncidencias.getincidencias() !=
    // null) {
    // listadoIncidencias = respuestaIncidencias.getincidencias();
    // } else {
    // System.out.println("Error: La respuesta es nula o no contiene cámaras.");
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return listadoIncidencias;
    // }

    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        peticionIncidenciasDeLaAPIMes();

    }

}
