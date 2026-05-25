package com.gymapp.gymapi.dto.response;


import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.gymapp.gymapi.model.User;
import com.gymapp.gymapi.model.enums.FitnessGoal;
import com.gymapp.gymapi.model.enums.Gender;
import com.gymapp.gymapi.model.enums.GymPreference;
import com.gymapp.gymapi.model.enums.SubscriptionTier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private UUID id;
    private String email;
    private String displayName;
    private String postcode;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Double heightCm;
    private Double weightKg;
    private String profilePhotoUrl;
    private String bio;
    private FitnessGoal fitnessGoal;
    private GymPreference gymPreference;
    private Integer yearsOfExperence;
    private String parkrunAthleteId;
    private Boolean stravaLinked;
    private Boolean profileComplete;
    private SubscriptionTier subscriptionTier;
    private Instant createdAt;


    // Converts a User entity into a UserResponse 

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .postcode(user.getPostcode())
                .subscriptionTier(user.getSubscriptionTier())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .heightCm(user.getHeightCm())
                .weightKg(user.getWeightKg())
                .profilePhotoUrl(user.getProfilePhotoUrl())
                .profileComplete(user.getProfileComplete())
                .bio(user.getBio())
                .fitnessGoal(user.getFitnessGoal())
                .gymPreference(user.getGymPreference())
                .yearsOfExperence(user.getYearsOfExperence())
                .parkrunAthleteId(user.getParkrunAthleteId())
                .stravaLinked(user.getStravaLinked())
                .createdAt(user.getCreatedAt())
                .build();
    }
}