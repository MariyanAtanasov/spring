package com.example.demo.dao;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserDao {
//
    @Autowired
    private UserRepository userRepository;

    public UserDao() {

    }

    public User getUserById(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User is not present in database!");
    }
}
