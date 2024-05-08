package com.example.task3jpamanytomany.service;

import com.example.task3jpamanytomany.Mapper.DoctorMapper;
import com.example.task3jpamanytomany.dao.entity.DoctorEntity;
import com.example.task3jpamanytomany.dao.repository.DoctorRepository;
import com.example.task3jpamanytomany.exception.DoctorIdNotFoundException;
import com.example.task3jpamanytomany.model.request.DoctorRequest;
import com.example.task3jpamanytomany.model.response.DoctorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {
    private final DoctorRepository doctorRepository;

    private final DoctorMapper doctorMapper;

    public DoctorResponse doctorSave(DoctorRequest doctorRequest) {
        log.info("ActionLog.doctorSave start");
        DoctorEntity doctorEntity = doctorMapper.mapToDoctor(doctorRequest);
        // List<PatientEntity> patientEntityList = patientRepository.findAllByIdIn(doctorRequest.getPatientIds());
        // doctorEntity.setPatients(patientEntityList);

        DoctorEntity saveDoctorEntity = doctorRepository.save(doctorEntity);
        log.info("ActionLog.doctorSave end");
        return doctorMapper.mapToResponse(saveDoctorEntity);
    }

    public List<DoctorResponse> getDoctors() {
        log.info("ActionLog.getDoctors start");

        List<DoctorEntity> doctors = doctorRepository.findAll();
        log.info("ActionLog.getDoctors end");

        return doctorMapper.mapToListResponse(doctors);
    }


    public DoctorResponse updateDoctor(Long id,  DoctorRequest doctorRequest) {
        log.info("ActionLog.updateDoctor start with id# "+id);
        DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(() -> new DoctorIdNotFoundException(HttpStatus.NOT_FOUND.name(), "Doctor id not found "));
        doctorMapper.mapForUpdate(doctorEntity, doctorRequest);
       doctorEntity= doctorRepository.save(doctorEntity);
        log.info("ActionLog.updateDoctor end");
        return doctorMapper.mapToResponse(doctorEntity);
    }

    public DoctorResponse getDoctor(Long id) {
        log.info("ActionLog.getDoctor start with id#" +id);

        DoctorEntity doctorEntity = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorIdNotFoundException(HttpStatus.NOT_FOUND.name(), "Doctor id not found"));

        var result = DoctorMapper.INSTANCE.mapToResponse(doctorEntity);
        log.info("ActionLog.getDoctor end");
        return result;

    }


    public ResponseEntity<?> deleteDoctor( Long id) {
        log.info("ActionLog.deleteDoctor start with id#" +id);
       // DoctorEntity doctorEntity = doctorRepository.findById(id).orElseThrow(() -> new DoctorIdNotFoundException(HttpStatus.NOT_FOUND.name(), "Doctor id not found "));
        doctorRepository.deleteById(id);
        log.info("ActionLog.deleteDoctor end");
        return ResponseEntity.ok("successfully");
    }
}
//contollerde method name status adi