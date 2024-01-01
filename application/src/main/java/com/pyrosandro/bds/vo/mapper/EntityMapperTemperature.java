package com.pyrosandro.bds.vo.mapper;

import com.pyrosandro.bds.model.Temperature;
import com.pyrosandro.bds.vo.TemperatureVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapperTemperature extends EntityMapper<TemperatureVO, Temperature> {}