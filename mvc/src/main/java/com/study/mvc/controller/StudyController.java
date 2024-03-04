package com.study.mvc.controller;


import com.study.mvc.model.HelloModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StudyController {

    // MVC
    @GetMapping("/hello")
    // 뷰 응답
    public String helloPage(Model model) {
//    public ModelAndView helloPage(Model model) {
//        model.addAttribute("name1", "김준일");
//        model.addAttribute("name2", "김준이");
//        model.addAttribute("name3", "김준삼");

        HelloModel helloModel = HelloModel.builder()
                .name1("김준일")
                .name2("김준이")
                .name3("김준삼")
                .build();

        model.addAttribute("helloModel", helloModel);
        return "hello";
    }

    // REST
    @GetMapping("/test")
    @ResponseBody   // 데이터 응답
    public Map<String, Object> TestPage() {
        Map<String, Object> testObj = new HashMap<>();
        testObj.put("age", 32);
        return testObj;
    }
}
