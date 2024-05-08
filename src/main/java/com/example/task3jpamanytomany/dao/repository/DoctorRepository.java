package com.example.task3jpamanytomany.dao.repository;

import com.example.task3jpamanytomany.dao.entity.DoctorEntity;
import com.example.task3jpamanytomany.dao.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {

    List<DoctorEntity> findAllByIdIn(List<Long> id);
    }
