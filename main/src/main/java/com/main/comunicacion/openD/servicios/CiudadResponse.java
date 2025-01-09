package com.main.comunicacion.openD.servicios;

import java.util.List;

import com.main.comunicacion.openD.DTOs.CiudadDTO;


import lombok.Data;

@Data
public class CiudadResponse {

    
    private List<CiudadDTO> ciudades; 
    

    
}
