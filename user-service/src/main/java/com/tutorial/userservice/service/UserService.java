package com.tutorial.userservice.service;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.modelos.Bike;
import com.tutorial.userservice.modelos.Car;
import com.tutorial.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface UserService {
   public List<User> getAll();
   public User getUserById(Long id);
   public User createUser(User user);
   //Uso de RestTemplate
   public List<Car> getCars(Long userId);

   public List<Bike> getBikes(Long userId);
   //FeignClient
   public Car saveCar(Long usuarioId, Car car);
   public Bike createBike(Long usuarioId, Bike bike);

   public Map<String,Object> getUserAndVehicles(Long userId);

}
