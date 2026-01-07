package com.project.project.Controller;

import com.project.project.DTO.MedicalProfileDTO;
import com.project.project.Entity.MedicalProfile;
import com.project.project.Entity.User;
import com.project.project.Service.MedicalProfileService;
import com.project.project.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://eaw-frontend.onrender.com")
public class ProfileController {

    private final MedicalProfileService medicalProfileService;
    private final UserService userService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // ========================
    // Create or Update Profile
    // ========================
    @PostMapping("/{userId}")
    public ResponseEntity<?> saveOrUpdateProfile(@PathVariable Long userId,
                                                 @RequestBody MedicalProfileDTO dto) {
        Optional<User> userOpt = userService.getUserById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();

        // If profile exists, update it. Otherwise, create new.
        MedicalProfile profile = medicalProfileService.getProfileByUser(user)
                .orElse(new MedicalProfile());
        profile.setUser(user);
        profile.setBloodGroup(dto.getBloodGroup());
        profile.setAllergies(dto.getAllergies());
        profile.setMedicalConditions(dto.getMedicalConditions());
        profile.setEmergencyContactName(dto.getEmergencyContactName());
        profile.setEmergencyContactRelationship(dto.getEmergencyContactRelationship());
        profile.setEmergencyContactPhone(dto.getEmergencyContactPhone());
        profile.setSuggestions(dto.getSuggestions());

        MedicalProfile saved = medicalProfileService.saveOrUpdateProfile(profile);

        MedicalProfileDTO responseDTO = MedicalProfileDTO.builder()
                .id(saved.getId())
                .userId(user.getId())
                .bloodGroup(saved.getBloodGroup())
                .allergies(saved.getAllergies())
                .medicalConditions(saved.getMedicalConditions())
                .emergencyContactName(saved.getEmergencyContactName())
                .emergencyContactRelationship(saved.getEmergencyContactRelationship())
                .emergencyContactPhone(saved.getEmergencyContactPhone())
                .suggestions(saved.getSuggestions())
                .lastUpdated(saved.getLastUpdated().format(formatter))
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    // ========================
    // Get Profile by User
    // ========================
    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        Optional<User> userOpt = userService.getUserById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Optional<MedicalProfile> profileOpt = medicalProfileService.getProfileByUser(userOpt.get());

        if (profileOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Profile not found");
        }

        MedicalProfile profile = profileOpt.get();

        MedicalProfileDTO dto = MedicalProfileDTO.builder()
                .id(profile.getId())
                .userId(userOpt.get().getId())
                .bloodGroup(profile.getBloodGroup())
                .allergies(profile.getAllergies())
                .medicalConditions(profile.getMedicalConditions())
                .emergencyContactName(profile.getEmergencyContactName())
                .emergencyContactRelationship(profile.getEmergencyContactRelationship())
                .emergencyContactPhone(profile.getEmergencyContactPhone())
                .suggestions(profile.getSuggestions())
                .lastUpdated(profile.getLastUpdated().format(formatter))
                .build();

        return ResponseEntity.ok(dto);
    }
}
