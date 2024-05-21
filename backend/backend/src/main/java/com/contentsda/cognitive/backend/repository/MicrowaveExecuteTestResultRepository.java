package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.MicrowaveExecuteTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicrowaveExecuteTestResultRepository extends JpaRepository<MicrowaveExecuteTestResult, Long> {
}
