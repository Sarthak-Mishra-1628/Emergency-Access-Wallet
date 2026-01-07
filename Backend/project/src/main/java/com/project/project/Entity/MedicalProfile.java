package com.project.project.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private String allergies;
    private String medicalConditions;
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private String emergencyContactPhone;
    private String suggestions;
    private LocalDateTime lastUpdated;
}
