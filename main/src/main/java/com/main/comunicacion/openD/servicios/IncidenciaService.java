package com.main.comunicacion.openD.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.comunicacion.mapeos.CiudadMap;
import com.main.comunicacion.mapeos.IncidenciaMap;
import com.main.comunicacion.openD.DTOs.CiudadDTO;
import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.repositorios.CiudadRepositorio;
import com.main.modelo.repositorios.IncidenciaRepositorio;



@Service
public class IncidenciaService {

    public String ano = "2023";
    public String mes = "1";

    public static final String URL_ANO = "https://api.euskadi.eus/traffic/v1.0/incidences/byYear/${ano}?_pagina=";

    @Autowired
    public IncidenciaRepositorio incidenciaRepositorio;

    @Autowired
    public CiudadRepositorio ciudadRepositorio;
   

    @Autowired
    public RestTemplate restTemplate;

    public void peticionIncidenciasDeLaAPIMes() {

        String baseUrl = "https://api.euskadi.eus/traffic/v1.0/incidences/byMonth/" + ano + "/" + mes
                + "?_page=${pagina}";

        try {

            String urlPrimeraPagina = baseUrl.replace("${pagina}", "1");

            IncidenciaResponse response = restTemplate.getForObject(urlPrimeraPagina, IncidenciaResponse.class);
            int paginasTotales = response.getTotalPages();

            for (int pagina = 1; pagina <= 10; pagina++) {
                String url = baseUrl.replace("${pagina}", String.valueOf(pagina));
                System.out.println("url " + url);
                IncidenciaResponse responsePagina = restTemplate.getForObject(url, IncidenciaResponse.class);

                //System.out.println("response " + responsePagina.getIncidences());
                if (responsePagina != null && responsePagina.getIncidences() != null) {

                    for (IncidenciaDTO incidenciaDTO : responsePagina.getIncidences()) {

                        Incidencia incidencia = new Incidencia();
                        Ciudad ciudad = new Ciudad();
                        String nombreCiudad;

                        incidencia = IncidenciaMap.toEntity(incidenciaDTO);

                        System.out.println("cityTown" + incidenciaDTO.getCityTown());

                        if (incidenciaDTO.getCityTown() != null) {
                            nombreCiudad = incidenciaDTO.getCityTown();

                            boolean ciudadRepe = ciudadExite(nombreCiudad);

                            if(!ciudadRepe){
                                CiudadDTO ciudadDTO = buscarCiudad(nombreCiudad);

                                ciudad = CiudadMap.toEntity(ciudadDTO);
                                System.out.println("nombreCiudad" + ciudad.toString());
                                
    
                                ciudadRepositorio.save(ciudad);
                            }

                          
                            
                        }

                        incidenciaRepositorio.save(incidencia);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CiudadDTO buscarCiudad(String nombreCiudad) throws JsonProcessingException {
        CiudadDTO ciudadDTO = null;
        RestTemplate restTemplate = new RestTemplate(); 
        String baseUrl = "https://nominatim.openstreetmap.org/search?q=" + nombreCiudad + "&format=json";
        System.out.println("URL de solicitud: " + baseUrl);

        try {
            

            ResponseEntity<List<CiudadDTO>> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CiudadDTO>>() {
            }
            );

            List<CiudadDTO> ciudades = response.getBody();
            if (ciudades != null && !ciudades.isEmpty()) {
                ciudadDTO = ciudades.get(0);
                System.out.println("Primera ciudad encontrada: " + ciudadDTO.getName());

            } else {
                System.out.println("No se encontraron resultados para la ciudad: " + nombreCiudad);
            }
        } catch (Exception e) {
        
        }

        return ciudadDTO;
    }

    

    public boolean ciudadExite(String nombreCiudad) {
        boolean result = ciudadRepositorio.existsByNombre(nombreCiudad);

        

        return result;

    }
    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        peticionIncidenciasDeLaAPIMes();

    }

}
