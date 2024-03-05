package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mvc.dto.StudentReqDto;
import com.study.mvc.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/** 문제
 * Controller 명: StudentController
 *
 * 메소드명: getStudentInfo()
 * 요청 메소드: Get
 * 요청 URL: /student
 * 요청 Params: name, age, phone, address
 * 응답데이터: JSON(name, age, phone, address)  -> 객체 or Map
 */

@RestController
public class StudentController {

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@CookieValue(required = false) String students, @RequestBody Student student) throws JsonProcessingException, UnsupportedEncodingException {
        // post(JSON) 요청 일 때는 @RequestBody 필요

        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> studentList = new ArrayList<>();
        int lastId = 0;

        System.out.println(students);

        if(students != null) {
            if(!students.isBlank()) {
                for(Object object : objectMapper.readValue(students, List.class)) { // List 객체 object로
                    Map<String, Object> studentMap = (Map<String, Object>) object;  // 다운캐스팅
                    studentList.add(objectMapper.convertValue(studentMap, Student.class));
                }
                lastId = studentList.get(studentList.size() - 1).getStudentId();    //마지막학생
            }
        }

        student.setStudentId((lastId + 1));
        studentList.add(student);

        String studentListJson = objectMapper.writeValueAsString(studentList);

        System.out.println(studentListJson);

        ResponseCookie responseCookie = ResponseCookie
                .from("students", URLEncoder.encode(studentListJson, "UTF-8"))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .build();

        return ResponseEntity
                .created(null)
                .header(HttpHeaders.SET_COOKIE,responseCookie.toString())
                .body(student);
    }

    @GetMapping("/student")
    public ResponseEntity<?> getStudentInfo(StudentReqDto studentReqDto) {
        System.out.println(studentReqDto);

        return ResponseEntity.badRequest().body(studentReqDto.toRespDto());
    }

    @GetMapping("/students/{studentId}")    // {} 동적
    public ResponseEntity<?> getStudent(@PathVariable int studentId) {
        List<Student> studentList = List.of(
                new Student(1, "김준일"),
                new Student(2, "김준이"),
                new Student(3, "김준삼"),
                new Student(4, "김준사")
        );

        // 존재하지 않는 ID
        Student findStudent = null;
        for(Student student : studentList) {
           if(student.getStudentId() == studentId) {
               findStudent = student;
           }
        }
        if(findStudent == null) {
            return  ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID 입니다."));
        }

        // stream , optional 사용
//        Optional<Student> optionalStudent =
//                studentList.stream().filter(student -> student.getStudentId() == studentId).findFirst();
//        if(optionalStudent.isEmpty()) {
//            return  ResponseEntity.badRequest().body(Map.of("errorMessage", "존재하지 않는 ID 입니다."));
//        }
//        findStudent = optionalStudent.get();

        return ResponseEntity.ok().body(studentId);
    }
}
