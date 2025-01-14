package com.main.comunicacion.openD.servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.main.comunicacion.mapeos.RegionMapper;
import com.main.comunicacion.openD.DTOs.RegionDTO;
import com.main.modelo.entidades.Region;
import com.main.modelo.repositorios.RegionRepository;

//Gestionará las peticiones a las regiones de open data
@Service
public class RegionService {

    private static final String API_URL = "https://api.euskadi.eus/traffic/v1.0/sources";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionMapper regionMapper;
 
    // Método para obtener y guardar todas las regiones desde la API
    public void fetchAndSaveAllRegions() {
        List<RegionDTO> regionDTOs = fetchRegionsFromApiResponse();
        if (regionDTOs == null || regionDTOs.isEmpty()) {
            return;
        }

        // Mapea y guarda las regiones obtenidas
        regionDTOs.forEach(dto -> {
            Region region = regionMapper.toEntity(dto);
            regionRepository.save(region); 
            System.out.println("Guardar region en la base de datos: " + region.getNombreEs()+" : " + region.getIdRegion());
        });
    }

    // Método para obtener la respuesta de la API usando exchange y ParameterizedTypeReference
    private List<RegionDTO> fetchRegionsFromApiResponse() {
        try {
            ResponseEntity<List<RegionDTO>> response = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RegionDTO>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Error durante la solicitud a la API: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

