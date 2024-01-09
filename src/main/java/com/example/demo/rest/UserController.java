package com.example.demo.rest;

import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

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
        userService.updateUser(user);
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

    @GetMapping(path = "/findById")
    public @ResponseBody UserModel findUserById(@RequestParam long id){
        try {
            return userService.getUserById(id);
        }
       catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping(path = "/delete")
    public String deleteUserById(@RequestParam long id){
        userRepository.deleteById(id);;
        return "User Deleted Successfully";
    }
}