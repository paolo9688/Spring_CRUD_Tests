package com.example.crud_test_exercise.service;

import com.example.crud_test_exercise.entity.User;
import com.example.crud_test_exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // crea un nuovo utente:
    public User createUser(User newUser){
        return userRepository.save(newUser);
    }
}
