package com.example.crud_test_exercise.repository;

import com.example.crud_test_exercise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
