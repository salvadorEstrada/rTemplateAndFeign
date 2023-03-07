package com.tutorial.userservice.feignclients;

import com.tutorial.userservice.modelos.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Aqui se hace la comunicación entre user-service y car-service
//@FeignClient(name="car-service", url="http://localhost:8002")//la url ya estaría registrada en eureka server
@FeignClient(name="car-service")
@RequestMapping("/car")
public interface CarroFeignClient {
   //Esto es gurdar un objeto si n usuar Jpa Respository
    @PostMapping()
    public Car save(@RequestBody Car car);
    //esta uri será mapeada al car-service
    @GetMapping("/user/{userId}")
    List<Car> getCar(@PathVariable Long userId);

}





