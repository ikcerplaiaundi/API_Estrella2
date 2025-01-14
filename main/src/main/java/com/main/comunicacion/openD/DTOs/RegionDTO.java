package com.main.comunicacion.openD.DTOs;

//DTO de region usado para mapeo opendata
public class RegionDTO {

    private Long id;
    private String descripcionEs;
    private String descripcionEu;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionEs() {
        return descripcionEs;
    }

    public void setDescripcionEs(String descripcionEs) {
        this.descripcionEs = descripcionEs;
    }

    public String getDescripcionEu() {
        return descripcionEu;
    }

    public void setDescripcionEu(String descripcionEu) {
        this.descripcionEu = descripcionEu;
    }
}
