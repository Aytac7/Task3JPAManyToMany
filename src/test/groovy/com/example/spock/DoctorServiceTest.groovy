package com.example.spock

import com.example.task3jpamanytomany.Mapper.DoctorMapper
import com.example.task3jpamanytomany.dao.entity.DoctorEntity
import com.example.task3jpamanytomany.dao.repository.DoctorRepository
import com.example.task3jpamanytomany.model.request.DoctorRequest
import com.example.task3jpamanytomany.model.response.DoctorResponse
import com.example.task3jpamanytomany.service.DoctorService
import groovy.transform.builder.Builder
import io.github.benas.randombeans.EnhancedRandomBuilder
import spock.lang.Specification


class DoctorServiceTest extends Specification {

    private DoctorRepository doctorRepository

    private DoctorMapper doctorMapper

    private DoctorService doctorService
    //private EnhancedRandom random= EnhancedRandomBuilder

    def setup() {
        doctorRepository = Mock()
        doctorMapper = Mock()
        doctorService = new DoctorService(doctorRepository, doctorMapper)
    }

    def "GetDoctor success"() {
        given:
        def id = 1L
        def doctorEntity = DoctorEntity.builder()
                .id(10L)
                .name("Aytac")
                .surname("Ramazanli")
                .build()

//        def doctorResponse = DoctorResponse.builder()
//                .id(10L)
//                .name("Aytac")
//                .build()

        when:
        def result = doctorService.getDoctor(id)

        then:
        1 * doctorRepository.findById(id) >> Optional.of(doctorEntity)

        result.id == doctorEntity.id
        result.name == doctorEntity.name
        result == DoctorMapper.INSTANCE.mapToResponse(doctorEntity)

//        result == DoctorResponse.builder()
//                .id(doctorEntity.id)
//                .name(doctorEntity.name)
//                .build()
    }

    def "GetDoctors"() {
        given:
        List<DoctorEntity> mockDoctorEntities = [new DoctorEntity(id: 1, name: "Aynur"), new DoctorEntity(id: 2, name: "Sevinc")]
        List<DoctorResponse> mockDoctorResponses = [new DoctorResponse(id: 1, name: "Aynur"), new DoctorResponse(id: 2, name: "Sevinc")]


        when:
        List<DoctorResponse> doctors = doctorService.getDoctors()

        then:
        1 * doctorRepository.findAll() >> mockDoctorEntities
        1 * doctorMapper.mapToListResponse(mockDoctorEntities) >> mockDoctorResponses

        doctors == mockDoctorResponses
    }

    def "SaveDoctor"() {
        given:
        def doctorEntity = DoctorEntity.builder()
                .name("Lala")
                .surname("Ahmadova")
                .build()

        def doctorResponse = DoctorResponse.builder()
                .name("Lala")
                .surname("Ahmadova")
                .build()

        def doctorRequest = DoctorRequest.builder()
                .name("Lala")
                .surname("Ahmadova")
                .build()


        when:
        def result = doctorService.doctorSave(doctorRequest)

        then:
        1 * doctorRepository.save(doctorEntity) >> doctorEntity
        1 * doctorMapper.mapToResponse(doctorEntity) >> doctorResponse

        result == doctorResponse
    }

    def "UpdateDoctor"() {
        given:
        def id = 1L
        def doctorEntity = DoctorEntity.builder()
                .name("Lala")
                .surname("Ahmedova")
                .build()


        def doctorRequest = DoctorRequest.builder()
                .name("Lala")
                .surname("Ahmadova")
                .build()


        when:
        def result = doctorService.updateDoctor(id, doctorRequest)

        then:
        1 * doctorRepository.findById(id) >> Optional.of(doctorEntity)
        1 * doctorMapper.mapForUpdate(_, _) >> { DoctorEntity entity, DoctorRequest request ->
            entity.setName(request.getName())
            entity.setSurname(request.getSurname())
        }
        1 * doctorRepository.save(doctorEntity) >> doctorEntity

        result == doctorMapper.mapToResponse(doctorEntity)

    }

    def "DeleteDoctor"() {
        given:
        def id = 1L
        def doctorEntity = DoctorEntity.builder()
                .name("Sefa")
                .surname("Veliyeva")
                .build()


        when:
        def result = doctorService.deleteDoctor(id)

        then:
        1 * doctorRepository.deleteById(id)

        result != null


    }
}
