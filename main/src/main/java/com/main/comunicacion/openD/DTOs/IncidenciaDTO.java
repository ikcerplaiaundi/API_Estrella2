package com.main.comunicacion.openD.DTOs;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO de incidencia usado para mapeo opendata
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaDTO {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private long incidenceId;
    private Long sourceId;
    private String incidenceType;
    private String autonomousRegion;
    private String province;
    private String carRegistration;
    private String cause;
    private String cityTown;
    private Date startDate;
    private String incidenceLevel;
    private String road;
    private String pkStart;
    private String pkEnd;
    private String direction;
    private String latitude;
    private String longitude;
    @Override
    public String toString() {
        return "IncidenciaDTO [incidenceId=" + incidenceId + ", sourceId=" + sourceId + ", incidenceType="
                + incidenceType + ", autonomousRegion=" + autonomousRegion + ", province=" + province
                + ", carRegistration=" + carRegistration + ", cause=" + cause + ", cityTown=" + cityTown
                + ", startDate=" + startDate + ", incidenceLevel=" + incidenceLevel + ", road=" + road + ", pkStart="
                + pkStart + ", pkEnd=" + pkEnd + ", direction=" + direction + ", latitude=" + latitude + ", longitude="
                + longitude + "]";
    }

    


}
