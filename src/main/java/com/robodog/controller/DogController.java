package com.robodog.controller;

import com.robodog.model.Dog;
import com.robodog.service.DogService;
import com.robodog.service.DogStorage;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/robo-dog")
public class DogController {


    private final DogService dogService;
    private final DogStorage dogStorage;
    public DogController(DogStorage dogStorage, DogService dogService) {
        this.dogStorage = dogStorage;
        this.dogService = dogService;
    }

    @GetMapping("/allDogs")
    public List<Dog> getAllDogs() {
        return dogStorage.getDogs();
    }

    @GetMapping("/random")
    public Dog getRandomDog() {
        dogStorage.addRandomDog();
        return dogStorage.getDogs().get(dogStorage.getDogs().size() - 1);
    }

    @PostMapping("/addDog")
    public Dog addDog(@RequestBody Dog dog) {
        dog.setDogId(dogStorage.getNextId());
        dogStorage.addDog(dog);
        return dog;
    }

    @PutMapping("/updateDog/{dogId}")
    public void updateDog(@PathVariable("dogId") Long dogId, @RequestBody Dog dog) {
        dogStorage.updateDog(dogId, dog.getName(), dog.getAge(), dog.getBreed());
    }

    @GetMapping("/{dogId}")
    public Dog getId(@PathVariable("dogId") Long dogId){
        return this.dogService.getDog(dogId);
    }
}
