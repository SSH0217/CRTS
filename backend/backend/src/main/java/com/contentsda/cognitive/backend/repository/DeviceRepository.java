package com.contentsda.cognitive.backend.repository;

import com.contentsda.cognitive.backend.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByOrganizationId(Long organizationId);
}
