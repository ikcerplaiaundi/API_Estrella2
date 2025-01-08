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
import com.main.comunicacion.mapeos.ProvinciaMap;
import com.main.comunicacion.openD.DTOs.CiudadDTO;
import com.main.comunicacion.openD.DTOs.IncidenciaDTO;
import com.main.comunicacion.openD.DTOs.ProvinciaDTO;
import com.main.modelo.entidades.Ciudad;
import com.main.modelo.entidades.Incidencia;
import com.main.modelo.entidades.Provincia;
import com.main.modelo.entidades.TipoIncidencia;
import com.main.modelo.repositorios.CiudadRepositorio;
import com.main.modelo.repositorios.IncidenciaRepositorio;
import com.main.modelo.repositorios.ProvinciaRepositorio;
import com.main.modelo.repositorios.TipoIncidenciaRepositorio;

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
    public ProvinciaRepositorio provinciaRepositorio;

    @Autowired
    public TipoIncidenciaRepositorio tipoIncidenciaRepositorio;

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
                // System.out.println("url " + url);
                IncidenciaResponse responsePagina = restTemplate.getForObject(url, IncidenciaResponse.class);

                //System.out.println("response " + responsePagina.getIncidences());
                if (responsePagina != null && responsePagina.getIncidences() != null) {

                    for (IncidenciaDTO incidenciaDTO : responsePagina.getIncidences()) {

                        Incidencia incidencia = new Incidencia();
                        Ciudad ciudad = new Ciudad();
                        Provincia provincia = new Provincia();
                        TipoIncidencia tipoIncidencia = new TipoIncidencia();
                        String nombreCiudad = "";
                        String nombreProvincia = "";
                        String nombreIncidencia = "";
                        CiudadDTO ciudadDTO = new CiudadDTO();
                        ProvinciaDTO provinciaDTO = new ProvinciaDTO();

                        if (incidenciaDTO.getProvince() != null) {

                            // System.out.println("cityTown" + incidenciaDTO.getProvince());
                            nombreProvincia = incidenciaDTO.getProvince();
                            // System.out.println("Provincia "+nombreProvincia);

                            if (nombreProvincia.contains("-")) {
                                nombreProvincia = nombreProvincia.split("-")[0].trim();

                            }
                            if (nombreProvincia.contains("/")) {
                                nombreProvincia = nombreProvincia.split("/")[0].trim();

                            }
                            if (nombreProvincia.equals("ARABA")) {
                                nombreProvincia = "Alava";
                            }
                            // System.out.println("Provincia2 "+nombreProvincia);
                            boolean provinciaRepe = provinciaExite(nombreProvincia);

                            if (!provinciaRepe) {
                                provinciaDTO = buscarProvincia(nombreProvincia);

                                provincia = ProvinciaMap.toEntity(provinciaDTO);

                                // System.out.println("provinciaNombre "+provincia.getNombre());
                                provinciaRepositorio.save(provincia);
                            }

                        }

                        if (incidenciaDTO.getCityTown() != null) {
                            nombreCiudad = incidenciaDTO.getCityTown();

                            boolean ciudadRepe = ciudadExite(nombreCiudad);

                            if (!ciudadRepe) {
                                ciudadDTO = buscarCiudad(nombreCiudad);

                                Provincia provinciaCiudad = buscarProvinciaPorNombre(provinciaDTO.getName());

                                ciudad = CiudadMap.toEntity(ciudadDTO);
                                ciudad.setProvincia(provinciaCiudad);
                                // System.out.println("nombreCiudad" + ciudad.toString());

                                ciudadRepositorio.save(ciudad);
                            }

                        }

                        if (incidenciaDTO.getIncidenceType() != null) {
                            nombreIncidencia = incidenciaDTO.getIncidenceType();

                            boolean incidenciaRepe = tipoIncidenciaExiste(nombreIncidencia);

                            if (!incidenciaRepe) {
                                tipoIncidencia.setNombre(nombreIncidencia);
                                incidenciaRepositorio.save(tipoIncidencia);
                            }

                        }

                        
                        if (incidenciaDTO.getProvince() != null && incidenciaDTO.getCityTown() != null) {
                            System.out.println("nombreCiudad" + nombreCiudad);
                            Ciudad ciudadIncidencia = buscarCiudadPorNombre(ciudadDTO.getName());

                            incidencia = IncidenciaMap.toEntity(incidenciaDTO);
                            incidencia.setCiudad(ciudadIncidencia);
                            incidenciaRepositorio.save(incidencia);
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Consultas provincia
    public ProvinciaDTO buscarProvincia(String nombreProvincia) throws JsonProcessingException {
        ProvinciaDTO provinciaDTO = null;
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "https://nominatim.openstreetmap.org/search?q=" + nombreProvincia + "&format=json";
        // System.out.println("URL de solicitud: " + baseUrl);

        try {

            ResponseEntity<List<ProvinciaDTO>> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ProvinciaDTO>>() {
            }
            );

            List<ProvinciaDTO> provincias = response.getBody();
            if (provincias != null && !provincias.isEmpty()) {
                provinciaDTO = provincias.get(0);
                // System.out.println("Primera ciudad encontrada: " + provinciaDTO.getName());

            } else {
                System.out.println("No se encontraron resultados para la provincia: " + nombreProvincia);
            }
        } catch (Exception e) {

        }

        return provinciaDTO;
    }

    public boolean provinciaExite(String nombreProvincia) {
        boolean result = provinciaRepositorio.existsByNombre(nombreProvincia);

        return result;
    }

    //Consultas para ciudad
    public CiudadDTO buscarCiudad(String nombreCiudad) throws JsonProcessingException {
        CiudadDTO ciudadDTO = null;
        RestTemplate restTemplate = new RestTemplate();

        String procesadoNombreCiudad = nombreCiudad.split("[, /-]")[0];
        String baseUrl = "https://nominatim.openstreetmap.org/search?q=" + procesadoNombreCiudad + "&format=json";
        // System.out.println("URL de solicitud: " + baseUrl);

        try {
            ResponseEntity<List<CiudadDTO>> response = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CiudadDTO>>() {
            });

            List<CiudadDTO> ciudades = response.getBody();
            if (ciudades != null && !ciudades.isEmpty()) {
                ciudadDTO = ciudades.get(0);
                // System.out.println("Primera ciudad encontrada: " + ciudadDTO.getName());
            } else {
                System.out.println("No se encontraron resultados para la ciudad: " + nombreCiudad);
            }
        } catch (Exception e) {
            return ciudadDTO;
        }

        return ciudadDTO;
    }

    public boolean ciudadExite(String nombreCiudad) {
        boolean result = ciudadRepositorio.existsByNombre(nombreCiudad);

        return result;

    }

    public Provincia buscarProvinciaPorNombre(String nombreProvincia) {
        Provincia provinciaCiudad = provinciaRepositorio.findByNombre(nombreProvincia);

        return provinciaCiudad;

    }

    //Consultas tipo incidencia
    public boolean tipoIncidenciaExiste(String nombreIncidencia) {
        boolean result = tipoIncidenciaRepositorio.existsByNombre(nombreIncidencia);

        return result;
    }

    //Consultas incidencia
    public Ciudad buscarCiudadPorNombre(String nombreCiudad) {
        Ciudad ciudadIncidencia = ciudadRepositorio.findByNombre(nombreCiudad);

        return ciudadIncidencia;

    }


    @EventListener(ContextRefreshedEvent.class)
    public void cargarDatosAlInicio() {
        peticionIncidenciasDeLaAPIMes();

    }

}
