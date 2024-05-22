package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findAllBySupervisionId(Long id);
}
