package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.DeskVisuospatialTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskVisuospatialTestResultRepository extends JpaRepository<DeskVisuospatialTestResult, Long> {
}
