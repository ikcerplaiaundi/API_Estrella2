package com.main.comunicacion.privadas.DTOs;

import com.main.comunicacion.openD.DTOs.RegionPrivateDTO;

import lombok.Data;

// DTO para cámaras privadas
@Data
public class CameraPrivateDTO {
    private Long id;
    private String cameraName;
    private String latitud;
    private String longitud;
    private String urlImage;
    private RegionPrivateDTO region;

    public CameraPrivateDTO(Long id, String cameraName, String latitud, String longitud, String urlImage, RegionPrivateDTO region) {
        this.id = id;
        this.cameraName = cameraName;
        this.latitud = latitud;
        this.longitud = longitud;
        this.urlImage = urlImage;
        this.region = region;
    }
    
}
