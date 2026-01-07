package com.project.project.Controller;

import com.project.project.DTO.AuthRequest;
import com.project.project.DTO.AuthResponse;
import com.project.project.DTO.UserDTO;
import com.project.project.Entity.User;
import com.project.project.Service.UserService;
import com.project.project.Security.JWTService;     
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://eaw-frontend.onrender.com")
public class AuthController {

    private final UserService userService;
    private final JWTService jwtService;           

    // ========================
    // User Signup
    // ========================
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);

        UserDTO dto = UserDTO.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();

        return ResponseEntity.ok(dto);
    }

    // ========================
    // User Login
    // ========================
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
        Optional<User> userOpt = userService.findByUsername(authRequest.getUsername());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(authRequest.getPassword())) {

                // ✅ Generate real JWT
                String token = jwtService.generateToken(user.getUsername());

                UserDTO dto = UserDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build();

                AuthResponse response = AuthResponse.builder()
                        .message("Login successful")
                        .user(dto)
                        .token(token)            // <-- real token here
                        .build();

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
