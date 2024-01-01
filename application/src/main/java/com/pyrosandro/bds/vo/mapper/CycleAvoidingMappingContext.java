package com.pyrosandro.bds.vo.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

import java.util.IdentityHashMap;
import java.util.Map;

// Introduced to avoid circular reference in one-to-many bidirectional relationship between Device and User
// https://stackoverflow.com/questions/59895166/mapstruct-bidirectional-mapping
// However, I didn't like to use the @Context annotation. In particular I didn't like to pass a new istance of
// context everytime I call a mapping method. Since for the purpose of this application I do not need to constantly
// go from one entity to another, I've preferred to change into unidirectional relationship from User to Device,
// so I can get rid this class.
public class CycleAvoidingMappingContext {
//    private Map<Object, Object> knownInstances = new IdentityHashMap<Object, Object>();
//
//    @BeforeMapping
//    public <T> T getMappedInstance(Object source, @TargetType Class<T> targetType) {
//        return (T) knownInstances.get( source );
//    }
//
//    @BeforeMapping
//    public void storeMappedInstance(Object source, @MappingTarget Object target) {
//        knownInstances.put( source, target );
//    }
}