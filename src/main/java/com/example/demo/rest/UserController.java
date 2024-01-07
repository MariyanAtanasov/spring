package com.example.demo.rest;

import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
@RestController
@RequestMapping(path = "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @PostMapping(path = "/update", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    void updateUser(@RequestBody UserModel user) {
        userService.updateEmployee(user);
    }

    @PostMapping(path = "/add")
    public @ResponseBody User addNewUser(@RequestParam String name
            ,@RequestParam String gender, @RequestParam String email) {
        User springUser = new User();
        springUser.setName(name);
        springUser.setGender(gender);
        springUser.setEmail(email);
        userRepository.save(springUser);
        return springUser;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
