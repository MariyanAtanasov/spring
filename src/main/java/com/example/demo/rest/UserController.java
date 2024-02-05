package com.example.demo.rest;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @PostMapping(path = "/update", consumes = MediaType.ALL_VALUE)
    public void updateUser(@RequestBody UserModel model) throws UserNotFoundException, SQLException {
        userService.updateUser(model);
    }
    @PostMapping(path = "/add")
    public @ResponseBody User addNewUser(@RequestBody UserModel model) {
        User springUser = new User();
        springUser.setName(model.getName());
        springUser.setGender(model.getGender());
        springUser.setEmail(model.getEmail());
        userRepository.save(springUser);
        return springUser;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/findById")
    public @ResponseBody UserModel findUserById(@RequestParam long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error", e);
        }
    }
    @DeleteMapping(path = "/delete")
    public String deleteUserById(@RequestParam long id) throws SQLException,UserNotFoundException{
        try {
                UserModel user = userService.getUserById(id);
                String userName = user.getName();
                userRepository.deleteById(id);
                return "User with name: \"" + userName + "\" is deleted from DB successfully";
            }
        catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
        catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error", e);
        }
    }
}