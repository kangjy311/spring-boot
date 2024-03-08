package com.study.mvc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Study {
    private int id;
    private String name;
    private int age;
    private LocalDateTime createDate;
}
