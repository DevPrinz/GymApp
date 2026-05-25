package com.gymapp.gymapi.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.gymapp.gymapi.model.enums.FitnessGoal;
import com.gymapp.gymapi.model.enums.Gender;
import com.gymapp.gymapi.model.enums.GymPreference;
import com.gymapp.gymapi.model.enums.SubscriptionTier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "supabase_id", unique = true, nullable = false)
    private UUID supabaseId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "display_name")
    private String displayName;

    private String postcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_tier")
    @Builder.Default
    private SubscriptionTier subscriptionTier = SubscriptionTier.FREE;

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "height_cm" )
    private Double heightCm;
    
    @Column(name = "weight_kg")
    private Double weightKg;
    
    @Column(name = "profile_photo_url")
    private String profilePhotoUrl; // Cloudinary URL
    
    @Column(name = "bio" , length = 300)
    private String bio;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "fitness_goal")
    private FitnessGoal fitnessGoal;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "gym_preference")
    private GymPreference gymPreference;
    
    @Column(name = "years_of_experence")
    private Integer yearsOfExperence; // how long have they been working out for
    
    @Column(name = "parkrun_athlete_id")
    private String parkrunAthleteId;
    
    @Column(name = "strava_linked")
    @Builder.Default
    private Boolean stravaLinked = false; // set to true if strava is connected
    
    @Column(name = "profile_complete")
    @Builder.Default
    private Boolean profileComplete = false; //set to true if profile has been completed
}