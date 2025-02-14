package com.main.modelo.repositorios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Region;

//Entida repositorio principal de camaras
public interface CameraRepository extends JpaRepository<Camera, Long> {
    List<Camera> findByRegion(Region region);
}