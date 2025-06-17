package com.example.crud_test_exercise.service;

import com.example.crud_test_exercise.entity.User;
import com.example.crud_test_exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // trova un utente per id:
    public Optional<User> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional;
        }
        return Optional.empty();
    }

    // cancella un utente:
    public Optional<User> deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return userOptional;
        }
        return Optional.empty();
    }

    // aggiorna un utente:
    public Optional<User> updateUser(Long id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userOptional.get().setName(userDetails.getName());
            userOptional.get().setSurname(userDetails.getSurname());
            userOptional.get().setWorking(userDetails.getWorking());
            User userUpdated = userRepository.save(userOptional.get());
            return Optional.of(userUpdated);
        }
        return Optional.empty();
    }
}
