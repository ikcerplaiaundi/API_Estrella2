package com.main.modelo.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidencias")
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String latitud;
    private String longitud;
    private String nombreIncidencia;
    private String nivelIncidencia;
    private String carretera;
    private Date fechaInicio;

    @ManyToOne
    private Region region;

    @ManyToOne
    private Ciudad ciudad;
    @ManyToOne
    private TiposIncidencia tiposIncidencia;

    

}
