package com.pyrosandro.bds.repository;

import com.pyrosandro.bds.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByDeviceIdentifier(String deviceIdentifier);
}
