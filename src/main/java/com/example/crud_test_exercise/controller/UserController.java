package com.example.crud_test_exercise.controller;

import com.example.crud_test_exercise.entity.User;
import com.example.crud_test_exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return ResponseEntity.ok(usersToFind);
    }

    // trova un utente per id:
    @GetMapping("/find-user-by-id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) {
        Optional<User> userToFind = userService.getUserById(id);

        if (userToFind.isPresent()) {
            return ResponseEntity.ok(userToFind);
        }
        return ResponseEntity.notFound().build();
    }

    // cancella un utente:
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Optional<User>> deleteUtente(@PathVariable Long id) {
        Optional<User> utenteToFind = userService.getUserById(id);

        if (utenteToFind.isPresent()) {
            Optional<User> utenteToDelete = userService.deleteUser(id);
            return ResponseEntity.ok(utenteToDelete);
        }
        return ResponseEntity.notFound().build();
    }

    // aggiorna un utente:
    @PutMapping("/update-user/{id}")
    public ResponseEntity<Optional<User>> updateUtente(@PathVariable Long id,
                                                       @RequestBody User userDetails) {
        Optional<User> userToFind = userService.getUserById(id);

        if (userToFind.isPresent()) {
            Optional<User> userToUpdate = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(userToUpdate);
        }
        return ResponseEntity.notFound().build();
    }
}
