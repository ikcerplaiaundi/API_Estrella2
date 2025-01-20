package com.main.comunicacion.privadas.DTOs;

public class CameraPrivateDTO {
    private Long id;
    private String cameraName;
    private String latitud;
    private String longitud;
    private String urlImage;
    private Long idRegion;

    public CameraPrivateDTO(Long id,  String cameraName, String latitud, String longitud, String urlImage, Long idRegion) {
        this.id = id;
        this.cameraName = cameraName;
        this.latitud = latitud;
        this.longitud = longitud;
        this.urlImage = urlImage;
        this.idRegion = idRegion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    // Getters and Setters

    
    
}

    