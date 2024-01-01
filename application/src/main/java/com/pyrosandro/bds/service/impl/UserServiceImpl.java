package com.pyrosandro.bds.service.impl;

import com.pyrosandro.bds.exception.BdsErrorConstants;
import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.model.User;
import com.pyrosandro.bds.repository.UserRepository;
import com.pyrosandro.bds.service.DeviceService;
import com.pyrosandro.bds.service.UserService;
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
@Transactional(rollbackFor = BdsException.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DeviceService deviceService;
    private final EntityMapperUser userMapper;
    private final EntityMapperDevice deviceMapper;

    @Override
    public Optional<User> findByUserIdentifier(String userIdentifier) {
        return userRepository.findByUserIdentifier(userIdentifier);
    }

    @Override
    public UserVO save(UserVO userVO) throws BdsException {
        Optional<Device> existingDevice = deviceService.findByDeviceIdentifier(userVO.getDevice().getDeviceIdentifier());
        if(existingDevice.isEmpty()) {
            throw new BdsException(BdsErrorConstants.DEVICE_IDENTIFIER_NOT_FOUND, new Object[]{userVO.getDevice().getDeviceIdentifier()}, HttpStatus.NOT_FOUND);
        }
        userVO.setDevice(deviceMapper.toVo(existingDevice.get()));
        Optional<User> existingUser = findByUserIdentifier(userVO.getUserIdentifier());
        if(existingUser.isPresent()) {
            throw new BdsException(BdsErrorConstants.USER_ALREADY_EXISTS, new Object[]{existingUser.get().getUserIdentifier()}, HttpStatus.BAD_REQUEST);
        }
        User entity = userRepository.save(userMapper.toEntity(userVO));
        return userMapper.toVo(entity);
    }

    @Override
    public UserVO update(UserVO userVO) throws BdsException {
        Optional<User> existingUserIdentifier = userRepository.findByUserIdentifier(userVO.getUserIdentifier());
        if(existingUserIdentifier.isPresent() && !existingUserIdentifier.get().getId().equals(userVO.getId())) {
            throw new BdsException(BdsErrorConstants.USER_IDENTIFIER_ALREADY_EXISTS, new Object[]{userVO.getUserIdentifier()}, HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = userRepository.findById(userVO.getId());
        if(existingUser.isEmpty()) {
            throw new BdsException(BdsErrorConstants.USER_NOT_FOUND, new Object[]{userVO.getId()}, HttpStatus.NOT_FOUND);
        }
//        By requirements, user cannot update a device (otherwise other users registered to the same device could receive unwanted modifies)
//        DeviceVO deviceVO = deviceService.update(userVO.getDevice());
//        userVO.setDevice(deviceVO);
        User user = userRepository.save(userMapper.toEntity(userVO));
        return userMapper.toVo(user);
    }

    @Override
    public UserVO partialUpdate(UserVO userVO) throws BdsException {

        Optional<User> existingUserIdentifier = userRepository.findByUserIdentifier(userVO.getUserIdentifier());
        if(existingUserIdentifier.isPresent() && !existingUserIdentifier.get().getId().equals(userVO.getId())) {
            throw new BdsException(BdsErrorConstants.USER_IDENTIFIER_ALREADY_EXISTS, new Object[]{userVO.getUserIdentifier()}, HttpStatus.BAD_REQUEST);
        }
        Optional<User> optionalExistingUser = userRepository.findById(userVO.getId());
        if(optionalExistingUser.isEmpty()) {
            throw new BdsException(BdsErrorConstants.USER_NOT_FOUND, new Object[]{userVO.getId()}, HttpStatus.NOT_FOUND);
        }
        User existingUser = optionalExistingUser.get();
        userMapper.partialUpdate(existingUser, userVO);
        userRepository.save(existingUser);
        return userMapper.toVo(existingUser);
    }

    @Override
    public List<UserVO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toVo)
                .collect(Collectors.toList());
    }

    @Override
    public UserVO findOne(Long id) throws BdsException {
        Optional<User> optionalExistingUser = userRepository.findById(id);
        if(optionalExistingUser.isEmpty()) {
            throw new BdsException(BdsErrorConstants.USER_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
        }
        return userMapper.toVo(optionalExistingUser.get());
    }

    @Override
    public void delete(Long id) throws BdsException {
        if(!userRepository.existsById(id)) {
            throw new BdsException(BdsErrorConstants.USER_NOT_FOUND, new Object[]{id}, HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
