package com.example.spock

import com.example.task3jpamanytomany.controller.DoctorController
import com.example.task3jpamanytomany.exception.error.ErrorResponse
import com.example.task3jpamanytomany.exception.handler.CustomerException
import com.example.task3jpamanytomany.model.request.DoctorRequest
import com.example.task3jpamanytomany.model.response.DoctorResponse
import com.example.task3jpamanytomany.service.DoctorService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.github.benas.randombeans.EnhancedRandomBuilder
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.ExceptionHandler
import org.xml.sax.ErrorHandler
import spock.lang.Specification

import java.time.LocalDate
import java.util.logging.Handler
//@injectmocks
//adlandirma tre ile
//lombok-database swagger testing dependency ardicilligi
//remark liquabase
//@operation
//restamplete feignclient
//@feignClient(name=){
//
// }
//model pac _>client
//esas classda @EnableFeignClients
class DoctorControllerTest extends Specification {

    MockMvc mockMvc
    DoctorService doctorService

    //ObjectMapper-JSON məlumatlarını Java obyektlərinə və Java obyektlərini JSON məlumatlarına çevirmək üçün istifadə edilən Jackson kitabxanasının bir hissəsidir.
    //JavaTimeModule Java 8 və sonrakı versiyalarda təqdim edilmiş tarix və vaxt siniflərinin (məsələn, LocalDate, LocalDateTime, ZonedDateTime və s.) JSON formatına çevrilməsinə imkan verir.

    def mapper = new ObjectMapper().registerModule(new JavaTimeModule())
    def random = EnhancedRandomBuilder.aNewEnhancedRandom()


    void "setup"() {
        doctorService = Mock()
        def doctorController = new DoctorController(doctorService)
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController)
                .setControllerAdvice(new CustomerException())
                .build()
    }

    def "GetStudent 200"() {
        given:
        def id = 1L
        def url = "/v1/doctors/$id"

        def doctorResponse = DoctorResponse.builder()
                .name("Aytac")
                .surname("Ramazanli")
                .dob(LocalDate.of(2004, 06, 15))
                .build()


        def responseJson = '''
             {
             "name":"Aytac",
             "surname":"Ramazanli",
             "dob":"2004-06-15"
             }
         '''

        when:
        def response = mockMvc
                .perform(MockMvcRequestBuilders.get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then:
        1 * doctorService.getDoctor(id) >> doctorResponse

        response.status == 200
        JSONAssert.assertEquals(responseJson, response.contentAsString, false)
    }
}
//swagger liqubase