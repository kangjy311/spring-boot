package com.study.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.mvc.diAndIoc.DiRepository;
import com.study.mvc.diAndIoc.DiService;
import com.study.mvc.diAndIoc.IoCService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/** IoC
 *
 * 의존 : 컨트롤러 -> 서비스 -> 레파지토리(@Component 안달아주면 @Autowired 도 안됨)
 * @Autowired 자동으로 생성해줌
 * Component 끼리만 IoC 됨 / RestController 에 Component 포함되어있음
 *
 * 스프링에서 bean : 객체
 */

@RestController
@RequiredArgsConstructor
public class IoCController {

//    @Autowired(required = false)    // 기본값 true
    private final IoCService ioCService;

    @GetMapping("/ioc")
    public ResponseEntity<?> iocTest() throws JsonProcessingException {
        String json = ioCService.getJson();

        return ResponseEntity.ok(json);
    }
}

