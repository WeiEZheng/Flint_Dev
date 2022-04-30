package com.example.flint.repository;

import com.example.flint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
}
