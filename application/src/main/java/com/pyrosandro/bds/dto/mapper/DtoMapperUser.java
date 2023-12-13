package com.pyrosandro.bds.dto.mapper;

import com.pyrosandro.bds.dto.UserDTO;
import com.pyrosandro.bds.vo.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface DtoMapperUser extends DtoMapper<UserDTO, UserVO> { }