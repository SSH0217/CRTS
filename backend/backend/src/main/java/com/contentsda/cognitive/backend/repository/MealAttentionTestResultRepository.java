package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.MealAttentionTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealAttentionTestResultRepository extends JpaRepository<MealAttentionTestResult, Long> {
}
