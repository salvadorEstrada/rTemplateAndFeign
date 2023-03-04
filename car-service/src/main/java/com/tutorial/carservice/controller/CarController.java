package com.tutorial.carservice.controller;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<?> listaCars(){
        List<Car> userList = carService.getAll();
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> getUsuarioId(@PathVariable Long carId){
        Car car = carService.getCarById(carId);
        if(car==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(car);
        }
    }

    @PostMapping
    public ResponseEntity<?> creaCar(@RequestBody Car car){
        Car newCar = carService.createCar(car);
        return  new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    //Aquí deberá estar la misma uri que está en interface CarFeignClient del microservicio user-service
    //http://localhost:8002/car/user/{userId}, el método findByUser se crea en el repositorio de JPA
   @GetMapping("user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId){
        List<Car> cars = carService.findByUserId(userId);
        if(cars==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(cars);
        }
    }

    /*@GetMapping("/user/{userId}")
    public ResponseEntity<Car> getByUserId(@PathVariable Long userId){
        return new  ResponseEntity(carService.findByUserId(userId), HttpStatus.OK);

    }*/

}
