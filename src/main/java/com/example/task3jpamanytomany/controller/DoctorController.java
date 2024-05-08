package com.example.task3jpamanytomany.controller;

import com.example.task3jpamanytomany.model.request.DoctorRequest;
import com.example.task3jpamanytomany.model.response.DoctorResponse;
import com.example.task3jpamanytomany.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/add")
    public DoctorResponse add(@RequestBody DoctorRequest doctorRequest) {
        return doctorService.doctorSave(doctorRequest);

    }

    @GetMapping
    public List<DoctorResponse> getDoctors() {
        return doctorService.getDoctors();
    }

    @PutMapping("/{id}")
    public DoctorResponse edit(@PathVariable Long id,
                                     @RequestBody DoctorRequest request) {
        return doctorService.updateDoctor(id, request);
    }


    @GetMapping("/{id}")
    public DoctorResponse getDoctor(@PathVariable Long id){
        return doctorService.getDoctor(id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id){
        doctorService.deleteDoctor(id);
    }

}