package com.pyrosandro.bds.service.impl;

import com.pyrosandro.bds.exception.BdsErrorConstants;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.model.Temperature;
import com.pyrosandro.bds.repository.TemperatureRepository;
import com.pyrosandro.bds.service.DeviceService;
import com.pyrosandro.bds.service.TemperatureService;
import com.pyrosandro.bds.vo.TemperatureVO;
import com.pyrosandro.bds.vo.mapper.EntityMapperDevice;
import com.pyrosandro.bds.vo.mapper.EntityMapperTemperature;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = BdsException.class)
@RequiredArgsConstructor
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;
    private final DeviceService deviceService;
    private final EntityMapperTemperature temperatureMapper;
    private final EntityMapperDevice deviceMapper;

    @Override
    public Optional<Temperature> findById(Long id) {
        return temperatureRepository.findById(id);
    }

    @Override
    public TemperatureVO save(TemperatureVO temperatureVO) throws BdsException {
        Optional<Device> existingDevice = deviceService.findByDeviceIdentifier(temperatureVO.getDevice().getDeviceIdentifier());
        if(existingDevice.isEmpty()) {
            throw new BdsException(BdsErrorConstants.DEVICE_IDENTIFIER_NOT_FOUND, new Object[]{temperatureVO.getDevice().getDeviceIdentifier()}, HttpStatus.NOT_FOUND);
        }
        temperatureVO.setDevice(deviceMapper.toVo(existingDevice.get()));
        Temperature entity = temperatureRepository.save(temperatureMapper.toEntity(temperatureVO));
        return temperatureMapper.toVo(entity);
    }

    @Override
    public TemperatureVO update(TemperatureVO temperatureVO) throws BdsException {
        Optional<Temperature> existingTemperature = findById(temperatureVO.getId());
        if(existingTemperature.isEmpty()) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_NOT_FOUND, new Object[]{temperatureVO.getId()}, HttpStatus.NOT_FOUND);
        }
        Temperature temperature = temperatureRepository.save(temperatureMapper.toEntity(temperatureVO));
        return temperatureMapper.toVo(temperature);
    }

    @Override
    public TemperatureVO partialUpdate(TemperatureVO temperatureVO) throws BdsException {
        Optional<Temperature> optionalExistingTemperature = findById(temperatureVO.getId());
        if(optionalExistingTemperature.isEmpty()) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_NOT_FOUND, new Object[]{temperatureVO.getId()}, HttpStatus.NOT_FOUND);
        }
        Temperature existingTemperature = optionalExistingTemperature.get();
        temperatureMapper.partialUpdate(existingTemperature, temperatureVO);
        temperatureRepository.save(existingTemperature);
        return temperatureMapper.toVo(existingTemperature);
    }

    @Override
    public List<TemperatureVO> findAll() {
        return temperatureRepository.findAll()
                .stream()
                .map(temperatureMapper::toVo)
                .collect(Collectors.toList());
    }

    @Override
    public TemperatureVO findOne(Long id) throws BdsException {
        Optional<Temperature> optionalExistingUser = temperatureRepository.findById(id);
        if(optionalExistingUser.isEmpty()) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
        }
        return temperatureMapper.toVo(optionalExistingUser.get());
    }

    @Override
    public void delete(Long id) throws BdsException {
        if(!temperatureRepository.existsById(id)) {
            throw new BdsException(BdsErrorConstants.TEMPERATURE_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
        }
        temperatureRepository.deleteById(id);
    }
}
