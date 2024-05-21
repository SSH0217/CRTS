package com.contentsda.cognitive.backend.service;

import com.contentsda.cognitive.backend.entity.ATestResult;
import com.contentsda.cognitive.backend.repository.ATestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClass {
    @Autowired
    private ATestResultRepository aTestResultRepository;

    public ATestResult saveATestResult(ATestResult aTestResult) {
        return aTestResultRepository.save(aTestResult);
    }
}
