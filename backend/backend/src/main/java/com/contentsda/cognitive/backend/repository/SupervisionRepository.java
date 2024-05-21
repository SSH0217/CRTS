package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.Supervision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisionRepository extends JpaRepository<Supervision, Long> {
    Supervision findByLoginId(String loginId);
}
