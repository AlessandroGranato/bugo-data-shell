package com.pyrosandro.bds.repository;

import com.pyrosandro.bds.model.Temperature;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {


    List<Temperature> findByDevice_DeviceIdentifierOrderByCreationDateDesc(String deviceIdentifier, Pageable pageable);
}
