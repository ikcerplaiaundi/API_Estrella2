package com.main.comunicacion.privadas.DTOs;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaPrivateDTO {

    private Integer id;
    private String latitud;
    private String longitud;
    private String causa;
    private String nivelIncidencia;
    private String carretera;
    private Integer idRegion;
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

}
