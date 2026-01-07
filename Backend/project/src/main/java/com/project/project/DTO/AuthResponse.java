package com.project.project.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String message;
    private UserDTO user;
    private String token; // placeholder if we add JWT later
}
