package com.main.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.modelo.entidades.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

    List<Region> findByIdRegion(Long idRegion);
}
