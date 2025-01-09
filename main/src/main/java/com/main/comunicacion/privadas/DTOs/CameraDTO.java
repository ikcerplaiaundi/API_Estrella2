package com.main.comunicacion.privadas.DTOs;

public class CameraDTO {
    
    private String cameraId;
    private String cameraName;
    private String latitud;
    private String longitud;
    private String urlImage;

    public CameraDTO(String cameraId, String cameraName, String latitud, String longitud, String urlImage) {
        this.cameraId = cameraId;
        this.cameraName = cameraName;
        this.latitud = latitud;
        this.longitud = longitud;
        this.urlImage = urlImage;
    }

    // Getters and Setters
    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
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
}
