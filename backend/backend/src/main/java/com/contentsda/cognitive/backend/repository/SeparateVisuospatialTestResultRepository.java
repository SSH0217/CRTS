package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.SeparateVisuospatialTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeparateVisuospatialTestResultRepository extends JpaRepository<SeparateVisuospatialTestResult, Long> {
}
