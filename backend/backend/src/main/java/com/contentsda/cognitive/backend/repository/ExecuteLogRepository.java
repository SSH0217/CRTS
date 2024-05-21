package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.ExecuteLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecuteLogRepository extends JpaRepository<ExecuteLog, Long> {
}
