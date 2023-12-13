package com.pyrosandro.bds.dto.mapper;

import com.pyrosandro.bds.dto.DeviceDTO;
import com.pyrosandro.bds.vo.DeviceVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface DtoMapperDevice extends DtoMapper<DeviceDTO, DeviceVO> { }