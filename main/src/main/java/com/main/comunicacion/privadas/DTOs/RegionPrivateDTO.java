package com.main.comunicacion.privadas.DTOs;

import java.util.List;

import lombok.Data;
//DTO de region de la api interna
@Data
public class RegionPrivateDTO {
    private Long id;
    private Long idRegion;
    private String nombreEs;
    private String nombreEu;
    private List<CameraPrivateDTO> cameras;
    

    public RegionPrivateDTO(Long id, Long idRegion, String nombreEs, String nombreEu, List<CameraPrivateDTO> cameras) {
        this.id = id;
        this.idRegion = idRegion;
        this.nombreEs = nombreEs;
        this.nombreEu = nombreEu;
        this.cameras = cameras;
        
    }

    
}
