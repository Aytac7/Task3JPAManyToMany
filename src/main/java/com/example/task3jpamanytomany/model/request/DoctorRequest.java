package com.example.task3jpamanytomany.model.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Builder
public class DoctorRequest {
    LocalDate dob;
    String name;
    String email;
    String address;
    String surname;
    String phoneNumber;
    String specialization;
    List<Long> patientIds;//patientIds
}
