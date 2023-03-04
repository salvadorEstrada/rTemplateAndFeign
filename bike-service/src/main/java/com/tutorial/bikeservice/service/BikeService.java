package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.entity.Bike;

import java.util.List;

public interface BikeService {
   public List<Bike> getAll();
   public Bike getCarById(Long id);
   public Bike createBike(Bike bike);
   List<Bike> findByUserId(Long userId);

}
