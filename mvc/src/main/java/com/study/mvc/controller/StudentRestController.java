package com.study.mvc.controller;

import com.study.mvc.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * 학생 추가 조회
 *
 *  Controller
 *      - StudentRestController
 *          - Get 요청: /ex/students         - 응답: OK [{"name": "전주환"}, {"name": "서창현"}, {"name": "예홍렬"}]
 *          - Get 요청: /ex/student/0        - 응답: OK {"name": "전주환"}
 *  Service
 *      - StudentService(interface)
 *          - getStudentList()
 *          - getStudent(int index)
 *      - StudentServiceImpl(class)
 *  Repository
 *      - StudentRepository(interface)
 *          - getStudentListAll()
 *          - findStudentNameByIndex(int index)
 *      - StudentRepositoryImpl(class)
 *          (전역에 List)
 *          - studentList = ["전주환", "서창현", "예홍렬"]
 */

@RestController
public class StudentRestController {

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;

    @GetMapping("/ex/students")
    public ResponseEntity<?> students() {

        return ResponseEntity.ok(studentService.getStudentList());
    }

    @GetMapping("/ex/student/{index}")
    public ResponseEntity<?> student(@PathVariable int index) {

        return ResponseEntity.ok(studentService.getStudent(index));
    }
}
