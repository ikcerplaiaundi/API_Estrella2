package com.main.comunicacion.openD.DTOs;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Integer incidenceId;
    private Integer sourceId;
    private String incidenceType;
    private String autonomousRegion;
    private String province;
    private String carRegistration;
    private String cause;
    private Date startDate;
    private String road;
    private String pkStart;
    private String pkEnd;
    private String direction;
    private String latitude;
    private String longitude;

    


}
