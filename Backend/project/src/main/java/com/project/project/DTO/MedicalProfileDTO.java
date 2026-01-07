package com.project.project.DTO;
import com.project.project.Entity.BloodGroup;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalProfileDTO {
    private Long id;
    private Long userId;
    private BloodGroup bloodGroup;
    private String allergies;
    private String medicalConditions;
    private String emergencyContactName;
    private String emergencyContactRelationship;
    private String emergencyContactPhone;
    private String suggestions;
    private String lastUpdated;
}
