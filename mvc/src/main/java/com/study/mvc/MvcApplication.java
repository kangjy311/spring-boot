package com.study.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvcApplication {

	public static void run() {
		System.out.println("기상");
		System.out.println("씻기");
		System.out.println("밥먹기");
		System.out.println("운동하기");
		System.out.println("공부하기");
	}

	public static void question(int questionNumber) {
		if(questionNumber == 1) {
			System.out.println("자바 질문");
		} else if (questionNumber == 2) {
			System.out.println("파이썬 질문");
		} else {
			System.out.println("모른다");
		}
	}

	public static void main(String[] args) {
		question(1);

		SpringApplication.run(MvcApplication.class, args);
	}

}
