package com.tutorial.bikeservice.controller;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<?> listaCars(){
        List<Bike> userList = bikeService.getAll();
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("/{bikeId}")
    public ResponseEntity<?> getUsuarioId(@PathVariable Long bikeId){
        Bike bike = bikeService.getCarById(bikeId);
        if(bike==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(bike);
        }
    }

    @PostMapping
    public ResponseEntity<?> creaBike(@RequestBody Bike bike){
        Bike newCar = bikeService.createBike(bike);
        return  new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    //Aquí deberá estar la misma uri que está en interface BikeFeignClient del microservicio user-service
    //http://localhost:8003/bike/user/{userId}
    @GetMapping("user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Long userId){
        List<Bike> bikes = bikeService.findByUserId(userId);
        if(bikes==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(bikes);
        }
    }

}
