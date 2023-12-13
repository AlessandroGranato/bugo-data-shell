package com.pyrosandro.bds.vo.mapper;

import com.pyrosandro.bds.model.User;
import com.pyrosandro.bds.vo.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface EntityMapperUser extends EntityMapper<UserVO, User> {}