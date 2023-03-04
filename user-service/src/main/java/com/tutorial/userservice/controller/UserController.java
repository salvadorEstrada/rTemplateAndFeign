package com.tutorial.userservice.controller;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.modelos.Bike;
import com.tutorial.userservice.modelos.Car;
import com.tutorial.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> listaUsuarios(){
        List<User> userList = userService.getAll();
        if(userList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUsuarioId(@PathVariable Long userId){
        User user = userService.getUserById(userId);
        if(user==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping
    public ResponseEntity<?> creaUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        return  new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //REST TEMPLATE

    @GetMapping("cars/{userId}")
    public ResponseEntity<?>  getCars(@PathVariable Long  userId){
        User user = userService.getUserById(userId);
        if(user==null){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(userService.getCars(userId));
    }

    @GetMapping("bikes/{userId}")
    public ResponseEntity<?>  getBikes(@PathVariable Long  userId){

        User user = userService.getUserById(userId);
        if(user==null){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(userService.getBikes(userId));
    }

    @PostMapping("car/{userId}")
    public ResponseEntity<?> guardarCarro(@PathVariable(value = "userId")Long userId,@RequestBody Car car){
        if(userService.getUserById(userId)==null)
            return ResponseEntity.notFound().build();
        Car newCar = userService.saveCar(userId,car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PostMapping("bike/{userId}")
    public ResponseEntity<?> guardarBike(@PathVariable(value="userId") Long userId, @RequestBody Bike bike ){
        if(userService.getUserById(userId)==null)
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(userService.createBike(userId, bike), HttpStatus.CREATED);
    }

    @GetMapping("/todos/{userId}")
    public ResponseEntity<?> getTodosLosVehiculos(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserAndVehicles(userId));
    }
}
