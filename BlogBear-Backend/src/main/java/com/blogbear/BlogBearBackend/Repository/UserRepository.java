package com.blogbear.BlogBearBackend.Repository;

import com.blogbear.BlogBearBackend.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
