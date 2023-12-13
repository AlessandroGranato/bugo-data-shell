package com.pyrosandro.bds.service.impl;

import com.pyrosandro.bds.exception.BdsErrorConstants;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.repository.DeviceRepository;
import com.pyrosandro.bds.service.DeviceService;
import com.pyrosandro.bds.vo.DeviceVO;
import com.pyrosandro.bds.vo.mapper.EntityMapperDevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final EntityMapperDevice deviceMapper;

    @Override
    public Optional<Device> findByDeviceIdentifier(String deviceIdentifier) {
        return deviceRepository.findByDeviceIdentifier(deviceIdentifier);
    }

    @Override
    public DeviceVO save(DeviceVO deviceVO) throws BdsException {
        Optional<Device> deviceIdentifier = findByDeviceIdentifier(deviceVO.getDeviceIdentifier());
        if(!deviceIdentifier.isEmpty()) {
            throw new BdsException(BdsErrorConstants.DEVICE_ALREADY_EXISTS, new Object[]{deviceIdentifier.get().getDeviceIdentifier()}, HttpStatus.BAD_REQUEST);
        }
        Device entity = deviceRepository.save(deviceMapper.toEntity(deviceVO));
        return deviceMapper.toVo(entity);
    }

    @Override
    public DeviceVO update(DeviceVO deviceVO) throws BdsException {
        if (!deviceRepository.existsById(deviceVO.getId())) {
            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{deviceVO.getId()}, HttpStatus.NOT_FOUND);
        }
        Optional<Device> existingDeviceIdentifier = deviceRepository.findByDeviceIdentifier(deviceVO.getDeviceIdentifier());
        if(!existingDeviceIdentifier.isEmpty() && existingDeviceIdentifier.get().getId() != deviceVO.getId()) {
            throw new BdsException(BdsErrorConstants.DEVICE_IDENTIFIER_ALREADY_EXISTS, new Object[]{deviceVO.getDeviceIdentifier()}, HttpStatus.BAD_REQUEST);
        }
        Device entity = deviceRepository.save(deviceMapper.toEntity(deviceVO));
        return deviceMapper.toVo(entity);
    }

    @Override
    public DeviceVO partialUpdate(DeviceVO deviceVO) throws BdsException {

        Optional<Device> optionalExistingDevice = deviceRepository.findById(deviceVO.getId());
        if(optionalExistingDevice.isEmpty()) {
            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{deviceVO.getId()}, HttpStatus.NOT_FOUND);
        }
        Optional<Device> existingDeviceIdentifier = deviceRepository.findByDeviceIdentifier(deviceVO.getDeviceIdentifier());
        if(!existingDeviceIdentifier.isEmpty() && existingDeviceIdentifier.get().getId() != deviceVO.getId()) {
            throw new BdsException(BdsErrorConstants.DEVICE_IDENTIFIER_ALREADY_EXISTS, new Object[]{deviceVO.getDeviceIdentifier()}, HttpStatus.BAD_REQUEST);
        }
        Device existingDevice = optionalExistingDevice.get();
        deviceMapper.partialUpdate(existingDevice, deviceVO);
        deviceRepository.save(existingDevice);
        return deviceMapper.toVo(existingDevice);
    }

    @Override
    public List<DeviceVO> findAll() {
        return deviceRepository.findAll()
                .stream()
                .map(deviceMapper::toVo)
                .collect(Collectors.toList());
    }

    @Override
    public DeviceVO findOne(Long id) throws BdsException {
        Optional<Device> optionalExistingDevice = deviceRepository.findById(id);
        if(optionalExistingDevice.isEmpty()) {
            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
        }
        return deviceMapper.toVo(optionalExistingDevice.get());
    }

    @Override
    public Optional<DeviceVO> findById(Long id) {
        return deviceRepository.findById(id).map(deviceMapper::toVo);
    }

    @Override
    public void delete(Long id) throws BdsException {
        if(!deviceRepository.existsById(id)) {
            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
        }
        deviceRepository.deleteById(id);
    }
}
