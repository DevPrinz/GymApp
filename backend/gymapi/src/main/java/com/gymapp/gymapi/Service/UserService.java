package com.gymapp.gymapi.Service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gymapp.gymapi.dto.request.UserUpdateRequest;
import com.gymapp.gymapi.dto.response.UserResponse;
import com.gymapp.gymapi.model.User;
import com.gymapp.gymapi.model.enums.SubscriptionTier;
import com.gymapp.gymapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository; 
	
	public UserResponse upsertUser(UUID supabaseId, String email, UserUpdateRequest request) {
		User user = userRepository.findBySupabaseId(supabaseId).orElse(User.builder()
				.supabaseId(supabaseId).email(email).subscriptionTier(SubscriptionTier.FREE).build());
		
		user.setDisplayName(request.getDisplayName());
		user.setPostcode(request.getPostcode());
		user.setUpdatedAt(Instant.now());
		return UserResponse.from(userRepository.save(user));
	}
	
	private boolean isProfileComplete(User user) {
		return user.getDisplayName() !=null &&
				user.getPostcode() !=null &&
				user.getDateOfBirth() !=null &&
				user.getGender() !=null &&
				user.getFitnessGoal() !=null &&
				user.getGymPreference() !=null;
	}
	
	public UserResponse updateProfile(UUID supabaseId, UserUpdateRequest request) {
		User user = userRepository.findBySupabaseId(supabaseId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		//Update fields that have been sent. Ignore nulls
		
		if(request.getDisplayName() !=null) user.setDisplayName(request.getDisplayName());
		if(request.getPostcode() !=null) user.setPostcode(request.getPostcode());
		if(request.getGender() !=null) user.setGender(request.getGender());
		if(request.getHeightCm() !=null) user.setHeightCm(request.getHeightCm());
		if(request.getWeightKg() !=null) user.setWeightKg(request.getWeightKg());
		if(request.getProfilePhotoUrl() !=null) user.setProfilePhotoUrl(request.getProfilePhotoUrl());
		if(request.getBio() !=null) user.setBio(request.getBio());
		if(request.getFitnessGoal() !=null) user.setFitnessGoal(request.getFitnessGoal());
		if(request.getGymPreference() !=null) user.setGymPreference(request.getGymPreference());
		if(request.getYearsOfExperence() !=null) user.setYearsOfExperence(request.getYearsOfExperence());
		if(request.getParkrunAthleteId() !=null) user.setParkrunAthleteId(request.getParkrunAthleteId());
		if(request.getDateOfBirth() !=null) user.setDateOfBirth(request.getDateOfBirth());
		
		user.setProfileComplete(isProfileComplete(user));
		user.setUpdatedAt(Instant.now());
		
		return UserResponse.from(userRepository.save(user));
	}
	
	public UserResponse getBySupabaseId(UUID supabaseId) {
	    User user = userRepository.findBySupabaseId(supabaseId)
	        .orElseThrow(() -> new RuntimeException("User not found"));
	    return UserResponse.from(user);
	}

}
