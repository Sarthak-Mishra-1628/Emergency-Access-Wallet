package com.project.project.Service;
import com.project.project.Entity.MedicalProfile;
import com.project.project.Entity.User;
import com.project.project.Repository.MedicalProfileRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalProfileService {

    private final MedicalProfileRepo medicalProfileRepo;

    // Create or update profile
    public MedicalProfile saveOrUpdateProfile(MedicalProfile profile) {
        profile.setLastUpdated(LocalDateTime.now());
        return medicalProfileRepo.save(profile);
    }

    // Get profile by user
    public Optional<MedicalProfile> getProfileByUser(User user) {
        return medicalProfileRepo.findByUser(user);
    }

    // Get by profile ID
    public Optional<MedicalProfile> getProfileById(Long id) {
        return medicalProfileRepo.findById(id);
    }
}
