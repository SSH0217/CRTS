package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.TestSubjectLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSubjectLogRepository extends JpaRepository<TestSubjectLog, Long> {
}
