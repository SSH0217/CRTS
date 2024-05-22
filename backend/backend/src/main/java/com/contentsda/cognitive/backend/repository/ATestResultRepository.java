package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.ATestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATestResultRepository extends JpaRepository<ATestResult, Long> {
    ATestResult findByTestResultId(Long testResultId);
}
