package com.study.mvc.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class StudentRespDto {
    private String name;
    private String age;
    private String phone;
    private String address;
}
