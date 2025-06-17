package com.example.crud_test_exercise.controller;

import com.example.crud_test_exercise.entity.User;
import com.example.crud_test_exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> userToFind = userService.getUserById(id);

        if (userToFind.isPresent()) {
            return ResponseEntity.ok(userToFind.get());
        }
        return ResponseEntity.notFound().build();
    }

    // cancella un utente per id:
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        Optional<User> utenteToDelete = userService.deleteUser(id);

        if (utenteToDelete.isPresent()) {
            return ResponseEntity.ok("User with ID " + utenteToDelete.get().getId()
                    + " has been successfully deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }

    // aggiorna un utente per id:
    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUtente(@PathVariable Long id,
                                                       @RequestBody User userDetails) {
        Optional<User> userToUpdate = userService.updateUser(id, userDetails);

        if (userToUpdate.isPresent()) {
            return ResponseEntity.ok(userToUpdate.get());
        }
        return ResponseEntity.notFound().build();
    }
}
