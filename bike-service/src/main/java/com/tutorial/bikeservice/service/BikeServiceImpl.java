package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {
    @Autowired
    BikeRepository bikeRepository;

    @Override
    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    @Override
    public Bike getCarById(Long id) {
        return bikeRepository.findById(id).orElse(null);
    }

    @Override
    public Bike createBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    @Override
    public List<Bike> findByUserId(Long userId) {
        return bikeRepository.findByUserId(userId);
    }
}
