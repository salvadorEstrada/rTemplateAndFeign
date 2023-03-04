package com.tutorial.userservice.feignclients;

import com.tutorial.userservice.modelos.Bike;
import com.tutorial.userservice.modelos.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Aqui se hace la comunicación entre user-service y bike-service
@FeignClient(name="bike-service", url="http://localhost:8003")
@RequestMapping("/bike")
public interface BikeFeignClient {
    @PostMapping()
    public Bike createBike(@RequestBody Bike bike);
    //esta uri será mapeada al bike-service
    @GetMapping("/user/{userId}")
    List<Bike> getBike(@PathVariable Long userId);
}
