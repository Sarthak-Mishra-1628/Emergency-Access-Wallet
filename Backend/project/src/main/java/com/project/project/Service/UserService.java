package com.project.project.Service;
import com.project.project.Entity.User;
import com.project.project.Repository.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    // Signup new user
    public User registerUser(User user) {
        // Check for existing username or email
        if (userRepo.findByUsername(user.getUsername()).isPresent()) 
            throw new IllegalArgumentException("Username already exists");
        
        if (userRepo.findByEmail(user.getEmail()).isPresent()) 
            throw new IllegalArgumentException("Email already exists");
        
        return userRepo.save(user);
    }

    // Find by username
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    // Find by email
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    // Get by ID
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }
}
