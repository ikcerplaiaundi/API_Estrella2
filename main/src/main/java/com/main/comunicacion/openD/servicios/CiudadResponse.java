package com.main.comunicacion.openD.servicios;

import java.util.List;

import com.main.comunicacion.openD.DTOs.CiudadDTO;

import lombok.Data;

//Gestionará el cuerpo data y meta de ciudades en open data
@Data
public class CiudadResponse {

    
    private List<CiudadDTO> ciudades; 
    

    
}
