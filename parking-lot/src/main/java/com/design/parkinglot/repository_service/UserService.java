package com.design.parkinglot.repository_service;


import com.design.parkinglot.model.User;
import com.design.parkinglot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        user = userRepository.save(user);
        return user;
    }

    public Boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    public User getUser(Long id) {
        User user = userRepository.getById(id);
        return user;
    }

    public User updateUser(User user){
        user = userRepository.save(user);
        return user;
    }
}
