package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.BTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTestResultRepository extends JpaRepository<BTestResult, Long> {
}
