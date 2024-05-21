package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.MartMemoryTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MartMemoryTestResultRepository extends JpaRepository<MartMemoryTestResult, Long> {
}
