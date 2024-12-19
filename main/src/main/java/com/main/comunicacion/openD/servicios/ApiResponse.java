package com.main.comunicacion.openD.servicios;

import java.util.List;

public class ApiResponse<T> {

    // Aquí puedes agregar metodos adicionales si es necesario
    
    private int totalItems =0; // Total de elementos en la respuesta
    private int totalPages = 1; // Total de paginas
    private int currentPage = 1; // Pagina actual
    private List<T> cameras; // Datos de la respuesta (cámaras u otros)
    // Getters y setters
    

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

    public List<T> getCameras() {
        return cameras;
    }

    public void setCameras(List<T> cameras) {
        this.cameras = cameras;
    }
}
