package com.main.comunicacion.openD.servicios;

import java.util.List;

import lombok.Data;

@Data
public class ApiResponse<T> {

    @Data
    public static class Response<T> {
        private List<T> data;         // Datos de la respuesta (e.g., incidencias)
        private int totalItems;       // Total de elementos en la respuesta
        private int totalPages;       // Total de páginas
        private int currentPage;      // Página actual
    }
}
