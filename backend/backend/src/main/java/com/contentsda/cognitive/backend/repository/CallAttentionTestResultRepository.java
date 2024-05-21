package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.CallAttentionTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallAttentionTestResultRepository extends JpaRepository<CallAttentionTestResult, Long> {
}
