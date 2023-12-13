package com.pyrosandro.bds.vo.mapper;

import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.vo.DeviceVO;
import org.mapstruct.Mapper;

//Doc - https://mapstruct.org/documentation/stable/reference/pdf/mapstruct-reference-guide.pdf Cap 4
@Mapper(componentModel = "spring", uses = {})
public interface EntityMapperDevice extends EntityMapper<DeviceVO, Device> {}