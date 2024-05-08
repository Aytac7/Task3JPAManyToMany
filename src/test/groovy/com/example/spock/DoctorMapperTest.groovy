package com.example.spock

import com.example.task3jpamanytomany.Mapper.DoctorMapper
import com.example.task3jpamanytomany.Mapper.DoctorMapperImpl
import com.example.task3jpamanytomany.dao.entity.DoctorEntity
import com.example.task3jpamanytomany.model.request.DoctorRequest
import com.example.task3jpamanytomany.model.response.DoctorResponse
import org.codehaus.groovy.ast.expr.UnaryMinusExpression
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DoctorMapperTest extends Specification {

    DoctorMapperImpl doctorMapper = new DoctorMapperImpl()
   // AutohoMapper m=AutoMapper.GetMapper(auto..)


    def "DoctorEntityToResponse"() {
        given:
        def doctorEntity = DoctorEntity.builder()
                .name("Aylin")
                .surname("Aliyeva")
                .build()

        when:
        def mapToDocResponse = doctorMapper.mapToResponse(doctorEntity)

        then:
        doctorEntity.name == mapToDocResponse.name
        doctorEntity.surname == mapToDocResponse.surname
    }

    def "MapToDoctor"() {
        given:
        def doctorRequest = DoctorRequest.builder()
                .name("Gunel")
                .surname("Qenberova")
                .build()

        when:
        def mapToDoctorEntity = doctorMapper.mapToDoctor(doctorRequest)

        then:
        doctorRequest.name == mapToDoctorEntity.name
        doctorRequest.surname == mapToDoctorEntity.surname
    }

    def "MapToListResponse"() {
        given:
        List<DoctorEntity> list = [new DoctorEntity(name: "Ulduz", surname: "Selimova"), new DoctorEntity(name: "Xalid", surname: "Selimov")]

        when:
        def mapListResponse = doctorMapper.mapToListResponse(list)

        then:
        list.surname == mapListResponse.surname
        list.name == mapListResponse.name
    }

    def "MapForUpdate"() {
        given:
        def doctorEntity = DoctorEntity.builder()
                .name("Gunel")
                .surname("Ahmedova")
                .build()

        def doctorRequest = DoctorRequest.builder()
                .name("Gunel")
                .surname("Ahmedova")
                .build()
        when:
        def result = doctorMapper.mapForUpdate(doctorEntity, doctorRequest)

        then:
        doctorEntity.name == doctorRequest.name
        doctorEntity.surname == doctorRequest.surname
    }
}
