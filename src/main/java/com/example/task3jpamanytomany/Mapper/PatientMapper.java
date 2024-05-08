package com.example.task3jpamanytomany.Mapper;

import com.example.task3jpamanytomany.dao.entity.PatientEntity;
import com.example.task3jpamanytomany.model.request.PatientRequest;
import com.example.task3jpamanytomany.model.response.PatientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public  interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    PatientEntity toPatientEntity(PatientRequest patientRequest);

    PatientResponse toPatientResponse(PatientEntity patientEntity);


    List<PatientResponse> mapToListResponse(List<PatientEntity> patientEntityList) ;


    // List<PatientResponse> patientRequestToPatientResponses(List<PatientRequest> patientRequestList);


}
