package com.example.crud_test_exercise.controller;

import com.example.crud_test_exercise.entity.User;
import com.example.crud_test_exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // crea un nuovo utente:
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    // trova tutti gli utenti:
    @GetMapping("/find-users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> usersToFind = userService.getUsers();
        return  ResponseEntity.ok(usersToFind);
    }
}
