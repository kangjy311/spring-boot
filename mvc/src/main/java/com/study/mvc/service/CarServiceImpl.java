package com.study.mvc.service;

import com.study.mvc.repository.CarRepository;
import com.study.mvc.repository.CarRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final String componentName = "a";

    @Autowired
    @Qualifier(componentName)
    private CarRepository carRepository;

    @Override
    public String getCarNames() {

        return String.join(", ", carRepository.getCarNames());
        // 문자열: k3, k5
    }

    @Override
    public int addCar(String carName) {
        return 0;
    }
}
