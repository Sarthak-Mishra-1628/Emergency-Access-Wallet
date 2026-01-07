package com.project.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.project.Entity.MedicalProfile;
import com.project.project.Entity.User;

import java.util.Optional;

public interface MedicalProfileRepo extends JpaRepository<MedicalProfile, Long>{
    Optional<MedicalProfile> findByUser(User user);
}