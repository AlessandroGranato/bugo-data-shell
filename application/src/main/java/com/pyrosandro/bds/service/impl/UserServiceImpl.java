package com.pyrosandro.bds.service.impl;

import com.pyrosandro.bds.exception.BdsErrorConstants;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.model.User;
import com.pyrosandro.bds.repository.DeviceRepository;
import com.pyrosandro.bds.repository.UserRepository;
import com.pyrosandro.bds.service.DeviceService;
import com.pyrosandro.bds.service.UserService;
import com.pyrosandro.bds.vo.DeviceVO;
import com.pyrosandro.bds.vo.UserVO;
import com.pyrosandro.bds.vo.mapper.EntityMapperDevice;
import com.pyrosandro.bds.vo.mapper.EntityMapperUser;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DeviceService deviceService;
    private final EntityMapperUser userMapper;

    @Override
    public Optional<User> findByUserIdentifier(Long userIdentifier) {
        return userRepository.findByUserIdentifier(userIdentifier);
    }

    @Override
    public UserVO save(UserVO userVO) throws BdsException {
        Optional<User> existingUser = findByUserIdentifier(userVO.getUserIdentifier());
        if(!existingUser.isEmpty()) {
            throw new BdsException(BdsErrorConstants.USER_ALREADY_EXISTS, new Object[]{existingUser.get().getUserIdentifier()}, HttpStatus.BAD_REQUEST);
        }

        Optional<DeviceVO> existingDevice = deviceService.findById(userVO.getDevice().getId());
        if(existingDevice.isEmpty()) {
            DeviceVO newDevice = deviceService.save(userVO.getDevice());
            userVO.setDevice(newDevice);
        }
        User entity = userRepository.save(userMapper.toEntity(userVO));
        return userMapper.toVo(entity);
    }

//    @Override
//    public DeviceVO update(DeviceVO deviceVO) throws BdsException {
//        if (!deviceRepository.existsById(deviceVO.getId())) {
//            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{deviceVO.getId()}, HttpStatus.NOT_FOUND);
//        }
//        Optional<Device> existingDeviceIdentifier = deviceRepository.findByDeviceIdentifier(deviceVO.getDeviceIdentifier());
//        if(!existingDeviceIdentifier.isEmpty() && existingDeviceIdentifier.get().getId() != deviceVO.getId()) {
//            throw new BdsException(BdsErrorConstants.DEVICE_IDENTIFIER_ALREADY_EXISTS, new Object[]{deviceVO.getDeviceIdentifier()}, HttpStatus.BAD_REQUEST);
//        }
//        Device entity = deviceRepository.save(deviceMapper.toEntity(deviceVO));
//        return deviceMapper.toVo(entity);
//    }
//
//    @Override
//    public DeviceVO partialUpdate(DeviceVO deviceVO) throws BdsException {
//
//        Optional<Device> optionalExistingDevice = deviceRepository.findById(deviceVO.getId());
//        if(optionalExistingDevice.isEmpty()) {
//            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{deviceVO.getId()}, HttpStatus.NOT_FOUND);
//        }
//        Optional<Device> existingDeviceIdentifier = deviceRepository.findByDeviceIdentifier(deviceVO.getDeviceIdentifier());
//        if(!existingDeviceIdentifier.isEmpty() && existingDeviceIdentifier.get().getId() != deviceVO.getId()) {
//            throw new BdsException(BdsErrorConstants.DEVICE_IDENTIFIER_ALREADY_EXISTS, new Object[]{deviceVO.getDeviceIdentifier()}, HttpStatus.BAD_REQUEST);
//        }
//        Device existingDevice = optionalExistingDevice.get();
//        deviceMapper.partialUpdate(existingDevice, deviceVO);
//        deviceRepository.save(existingDevice);
//        return deviceMapper.toVo(existingDevice);
//    }
//
//    @Override
//    public List<DeviceVO> findAll() {
//        return deviceRepository.findAll()
//                .stream()
//                .map(deviceMapper::toVo)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public DeviceVO findOne(Long id) throws BdsException {
//        Optional<Device> optionalExistingDevice = deviceRepository.findById(id);
//        if(optionalExistingDevice.isEmpty()) {
//            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
//        }
//        return deviceMapper.toVo(optionalExistingDevice.get());
//    }
//
//    @Override
//    public void delete(Long id) throws BdsException {
//        if(!deviceRepository.existsById(id)) {
//            throw new BdsException(BdsErrorConstants.DEVICE_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
//        }
//        deviceRepository.deleteById(id);
//    }
}
