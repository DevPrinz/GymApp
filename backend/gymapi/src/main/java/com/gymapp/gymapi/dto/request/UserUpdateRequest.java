package com.gymapp.gymapi.dto.request;

import com.gymapp.gymapi.model.enums.FitnessGoal;
import com.gymapp.gymapi.model.enums.Gender;
import com.gymapp.gymapi.model.enums.GymPreference;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String displayName;
    private String postcode;
    private Gender gender;
    private Double heightCm;
    private Double weightKg;
    private String profilePhotoUrl;
    private String bio;
    private FitnessGoal fitnessGoal;
    private GymPreference gymPreference;
    private Integer yearsOfExperence;
    private String parkrunAthleteId;
}