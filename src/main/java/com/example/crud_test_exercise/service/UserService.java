package com.example.crud_test_exercise.service;

import com.example.crud_test_exercise.entity.User;
import com.example.crud_test_exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // crea un nuovo utente:
    public User createUser(User user){
        return userRepository.save(user);
    }

    // trova tutti gli utenti:
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
