package com.pyrosandro.bds.dto.mapper;

import com.pyrosandro.bds.dto.TemperatureDTO;
import com.pyrosandro.bds.vo.TemperatureVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoMapperTemperature extends DtoMapper<TemperatureDTO, TemperatureVO> { }