package com.main.comunicacion.openD.servicios;

import java.util.List;

public class ApiResponse<T> {

    // Aquí puedes agregar metodos adicionales si es necesario
    private List<T> data; // Datos de la respuesta (cámaras u otros)
    private int totalItems; // Total de elementos en la respuesta
    private int totalPages = 1; // Total de paginas
    private int currentPage = 1; // Pagina actual

    // Getters y setters
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
