package com.pyrosandro.bds.vo.mapper;

import com.pyrosandro.bds.model.Temperature;
import com.pyrosandro.bds.model.User;
import com.pyrosandro.bds.vo.TemperatureVO;
import com.pyrosandro.bds.vo.UserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface EntityMapperTemperature extends EntityMapper<TemperatureVO, Temperature> {}