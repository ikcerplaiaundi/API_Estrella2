package com.main.modelo.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}