package com.tutorial.userservice.service;

import com.tutorial.userservice.entity.User;
import com.tutorial.userservice.feignclients.BikeFeignClient;
import com.tutorial.userservice.feignclients.CarroFeignClient;
import com.tutorial.userservice.modelos.Bike;
import com.tutorial.userservice.modelos.Car;
import com.tutorial.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private BikeFeignClient bikeFeignClient;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    //restTemplate
    @Override
    public List<Car> getCars(Long userId) {
        return restTemplate.getForObject("http://localhost:8002/car/byuser/"+userId, List.class);
    }

    @Override
    public List<Bike> getBikes(Long userId) {
        return restTemplate.getForObject("http://localhost:8003/bike/byuser/"+userId, List.class);
    }

   //Feignclients
   //Usa la interfaz CarroFeignClient para guarda el objeto  instead of JpaRepository
    public Car saveCar(Long usuarioId, Car car){
        car.setUserId(usuarioId);
        Car newCar= carroFeignClient.save(car);
        return newCar;
    }

    //Usa la interfaz BikeFeignClient para guarda el objeto  instead of JpaRepository
    @Override
    public Bike createBike(Long usuarioId, Bike bike) {
            bike.setUserId(usuarioId);
            Bike newBike = bikeFeignClient.createBike(bike);
        return newBike;
    }

    public Map<String,Object> getUserAndVehicles(Long userId){
        Map<String, Object > resultado = new HashMap<>();
        User user= userRepository.findById(userId)
                .orElseThrow();

       if(user==null){
            resultado.put("Mensaje","El usuario no existe");
            return resultado;
        }
        resultado.put("User",user);
        List<Car>  cars = carroFeignClient.getCar(userId);
        if(cars.isEmpty()){
            resultado.put("Cars", "El usuario no tiene carros");
        }else{
            resultado.put("Cars", cars);
        }
        List<Bike>  motos = bikeFeignClient.getBike(userId);

        if(motos.isEmpty()){
            resultado.put("Bikes","El usuario no tiene motos");
        }else{
            resultado.put("Bikes", motos);
        }
        return resultado;
    }

}
