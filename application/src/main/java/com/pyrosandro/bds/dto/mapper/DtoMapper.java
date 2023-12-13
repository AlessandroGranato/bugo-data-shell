package com.pyrosandro.bds.dto.mapper;

import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

public interface DtoMapper<D, V> {
    D toDto(V vo);

    @Mappings({
            @Mapping(target = "creationDate", source = "creationDate", qualifiedByName = "mapCreationDate")
    })
    V toVo(D dto);
    List<D> toDtoList(List<V> voList);
    List<V> toVoList(List<D> dtoList);

    @Named("partialDtoVoUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget V vo, D dto);

    @Named("mapCreationDate")
    default LocalDateTime mapDate(LocalDateTime date) {
        return date != null ? date : LocalDateTime.now();
    }
}
