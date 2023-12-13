package com.pyrosandro.bds.repository;

import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {

}
