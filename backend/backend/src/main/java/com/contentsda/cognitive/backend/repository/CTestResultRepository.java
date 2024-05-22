package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.CTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CTestResultRepository extends JpaRepository<CTestResult, Long> {
    CTestResult findByTestResultId(Long testResultId);
}
