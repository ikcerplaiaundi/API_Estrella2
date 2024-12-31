package com.main.comunicacion.openD.servicios;

import java.util.List;

import lombok.Data;

@Data
public class IncidenciaResponse<T> {

    // Aquí puedes agregar metodos adicionales si es necesario
    
    private int totalItems =0; // Total de elementos en la respuesta
    private int totalPages = 1; // Total de paginas
    private int currentPage = 1; // Pagina actual
    private List<T> incidences; // Datos de la respuesta (cámaras u otros)
    

    
}
