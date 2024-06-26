package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Override
    Optional<Organization> findById(Long aLong);
}
