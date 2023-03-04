package com.tutorial.carservice.service;

import com.tutorial.carservice.entity.Car;

import java.util.List;

public interface CarService {
   public List<Car> getAll();
   public Car getCarById(Long id);
   public Car createCar(Car car);
   List<Car> findByUserId(Long userId);

}
