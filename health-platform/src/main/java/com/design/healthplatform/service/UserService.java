package com.design.healthplatform.service;

import com.design.healthplatform.model.User;
import com.design.healthplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUser(User user){
        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    public ResponseEntity<User> getUser(Long id) {
        User user = userRepository.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
