package com.example.task3jpamanytomany.service;

import com.example.task3jpamanytomany.Mapper.PatientMapper;
import com.example.task3jpamanytomany.dao.entity.DoctorEntity;
import com.example.task3jpamanytomany.dao.entity.PatientEntity;
import com.example.task3jpamanytomany.dao.repository.DoctorRepository;
import com.example.task3jpamanytomany.dao.repository.PatientRepository;
import com.example.task3jpamanytomany.model.request.PatientRequest;
import com.example.task3jpamanytomany.model.response.PatientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private  final DoctorRepository doctorRepository;
    public PatientResponse savePatient(PatientRequest patientRequest){

       PatientEntity patientEntity= patientMapper.toPatientEntity(patientRequest);
       List<DoctorEntity> doctorEntities=doctorRepository.findAllByIdIn(patientRequest.getDoctorIds());
       patientEntity.setDoctors(doctorEntities);

       PatientEntity patientEntitySave=patientRepository.save(patientEntity);
       return patientMapper.toPatientResponse(patientEntitySave);
    }

    public List<PatientResponse> getPatients(){
        List<PatientEntity> patients=patientRepository.findAll();
       return patientMapper.mapToListResponse(patients);
    }

}
