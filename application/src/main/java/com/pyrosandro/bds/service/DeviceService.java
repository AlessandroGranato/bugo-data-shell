package com.pyrosandro.bds.service;

import com.pyrosandro.bds.dto.DeviceDTO;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.vo.DeviceVO;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    Optional<Device> findByDeviceIdentifier(String deviceIdentifier);

    DeviceVO save(DeviceVO deviceVO) throws BdsException;

    DeviceVO update(DeviceVO deviceVO) throws BdsException;

    DeviceVO partialUpdate(DeviceVO deviceVO) throws BdsException;

    List<DeviceVO> findAll();

    DeviceVO findOne(Long id) throws BdsException;

    Optional<DeviceVO> findById(Long id);

    void delete(Long id) throws BdsException;
}
