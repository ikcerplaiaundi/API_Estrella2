package com.main.comunicacion.openD.DTOs;

//DTO de camara usado para mapeo opendata
public class CameraDTO {
    private Long id;
    private Long sourceId;
    private String cameraName;
    private String latitude;
    private String longitude;
    private String road;
    private String kilometer;
    private String address;
    private String urlImage;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long cameraId) {
        this.id = cameraId;  // Corregido, se asigna cameraId a id
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;  // Corregido, se asigna cameraId a id
    }


    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}