package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.WashingMachineExecuteTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashingMachineExecuteTestResultRepository extends JpaRepository<WashingMachineExecuteTestResult, Long> {
}
