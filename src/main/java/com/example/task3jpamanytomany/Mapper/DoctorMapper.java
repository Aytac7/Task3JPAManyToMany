package com.example.task3jpamanytomany.Mapper;

import com.example.task3jpamanytomany.dao.entity.DoctorEntity;

import com.example.task3jpamanytomany.model.request.DoctorRequest;
import com.example.task3jpamanytomany.model.response.DoctorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DoctorMapper {
   DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    DoctorEntity mapToDoctor(DoctorRequest doctorRequest);

    DoctorResponse mapToResponse(DoctorEntity doctorEntity);

    List<DoctorResponse> mapToListResponse(List<DoctorEntity> doctorEntityList);

    void mapForUpdate(@MappingTarget DoctorEntity doctorEntity,DoctorRequest doctorRequest);
}


