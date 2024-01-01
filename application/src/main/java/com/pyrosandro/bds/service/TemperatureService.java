package com.pyrosandro.bds.service;

import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.model.Temperature;
import com.pyrosandro.bds.vo.TemperatureVO;

import java.util.List;
import java.util.Optional;

public interface TemperatureService {

    Optional<Temperature> findById(Long id);

    TemperatureVO save(TemperatureVO temperatureVO) throws BdsException;

    TemperatureVO update(TemperatureVO temperatureVO) throws BdsException;

    TemperatureVO partialUpdate(TemperatureVO temperatureVO) throws BdsException;

    List<TemperatureVO> findAll();

    TemperatureVO findOne(Long id) throws BdsException;

    void delete(Long id) throws BdsException;
}
