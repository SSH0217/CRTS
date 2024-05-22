package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.TestSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestSubjectRepository extends JpaRepository<TestSubject, Long> {
    List<TestSubject> findAllBySupervisionId(Long supervisionId);
}
