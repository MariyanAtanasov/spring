package com.example.demo.services;

import com.example.demo.dao.UserDao;
import com.example.demo.entities.User;
import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;

    public UserService(){

    }

    public void updateEmployee(UserModel userModel) {
        Optional<User> optionalEmployee = userRepository.findById(userModel.getId());
        User user = optionalEmployee.get();
        if (!"".equals(userModel.getId()) | !"".equals(userModel.getName()) | !"".equals(userModel.getGender()) | !"".equals(userModel.getEmail())) {
            user.setId(userModel.getId());
            user.setGender(userModel.getGender());
            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            userRepository.save(user);
        }
    }

    private UserModel getModel(User user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setGender(user.getGender());
        model.setEmail(user.getEmail());
        return model;
    }
}
