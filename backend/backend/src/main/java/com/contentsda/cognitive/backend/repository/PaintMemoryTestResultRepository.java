package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.PaintMemoryTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintMemoryTestResultRepository extends JpaRepository<PaintMemoryTestResult, Long> {
}
