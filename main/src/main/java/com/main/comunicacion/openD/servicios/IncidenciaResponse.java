package com.main.comunicacion.openD.servicios;

import java.util.List;

import com.main.comunicacion.openD.DTOs.IncidenciaDTO;

import lombok.Data;

@Data
public class IncidenciaResponse {

    
    
    private int totalItems =0; 
    private int totalPages = 1; 
    private int currentPage = 1; 
    private List<IncidenciaDTO> incidences; 
    

    
}
