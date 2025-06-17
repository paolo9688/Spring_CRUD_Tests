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
        User newUser = userRepository.save(user);

        return newUser;
    }

    // trova tutti gli utenti:
    public List<User> getUsers() {
        List<User> listUsers = userRepository.findAll();

        return listUsers;
    }

    // trova un utente per id:
    public Optional<User> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional;
    }

    // cancella un utente per id:
    public Optional<User> deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return userOptional;
        }
        return Optional.empty();
    }

    // aggiorna un utente per id:
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
