package com.main.modelo.repositorios;

import com.main.modelo.entidades.Camera;
import com.main.modelo.entidades.Region;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findByIdRegion(Long idRegion);
}
