package com.example.task3jpamanytomany.controller;

import com.example.task3jpamanytomany.model.request.PatientRequest;
import com.example.task3jpamanytomany.model.response.PatientResponse;
import com.example.task3jpamanytomany.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public PatientResponse add(@RequestBody PatientRequest patientRequest){
        return patientService.savePatient(patientRequest);
    }

    @GetMapping
    public List<PatientResponse> getData(){
        return patientService.getPatients();
    }

}
