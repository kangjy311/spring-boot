package com.study.mvc.controller;

import com.study.mvc.dto.HelloDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController     // Controller + ResponseBody -> 모든 메소드 데이터 응답 (html 못만듦)
public class StudyRestController {

    @GetMapping("/hello/test")
    public String hello(HelloDto helloDto) {
        // parseInt 형변환 알아서 해줌

        System.out.println(helloDto);

        return "Hello";
    }
}
