package com.pyrosandro.bds.service;

import com.pyrosandro.bds.exception.BdsException;
import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.model.User;
import com.pyrosandro.bds.vo.DeviceVO;
import com.pyrosandro.bds.vo.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findByUserIdentifier(Long userIdentifier);

    UserVO save(UserVO userVO) throws BdsException;

//    UserVO update(UserVO userVO) throws BdsException;
//
//    UserVO partialUpdate(UserVO userVO) throws BdsException;
//
//    List<UserVO> findAll();
//
//    UserVO findOne(Long id) throws BdsException;
//
//    void delete(Long id) throws BdsException;
}
