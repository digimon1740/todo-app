package com.kakao.todoapp.utils.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Slf4j
@Component
public class ModelMapperUtils {

    private ModelMapper modelMapper;

    @Autowired
    private ModelMapperUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T convertToDto(Object entity, Class<T> dto) {
        T returnDto = modelMapper.map(entity, dto);
        return returnDto;
    }

    public <T> T convertToDto(Object entity, Type dto) {
        T returnDto = modelMapper.map(entity, dto);
        return returnDto;
    }

    public <T> T convertToEntity(Object dto, Class<T> entity) {
        T returnEntity = modelMapper.map(dto, entity);
        return returnEntity;
    }

    public <T> T convertToEntity(Object dto, Type entity) {
        T returnEntity = modelMapper.map(dto, entity);
        return returnEntity;
    }
}
