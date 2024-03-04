package com.study.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor  // 값이 전체가 필수가 아니게 됨 AllArgs는 필수
@Data

public class HelloDto {
    private String name;
    private int age;
}
