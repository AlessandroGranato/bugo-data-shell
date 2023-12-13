package com.pyrosandro.bds.vo.mapper;

import com.pyrosandro.bds.model.Device;
import com.pyrosandro.bds.vo.DeviceVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface EntityMapper<V, E> {
    E toEntity(V vo);
    V toVo(E entity);
    List<E> toEntityList(List<V> voList);
    List<V> toVoList(List<E> entityList);

    @Named("partialVoEntityUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, V vo);
}
