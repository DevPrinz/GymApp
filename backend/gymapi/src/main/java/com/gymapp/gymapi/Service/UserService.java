package com.gymapp.gymapi.Service;

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
		return UserResponse.from(userRepository.save(user));
	}
	
	public UserResponse getBySupabaseId(UUID supabaseId) {
	    User user = userRepository.findBySupabaseId(supabaseId)
	        .orElseThrow(() -> new RuntimeException("User not found"));
	    return UserResponse.from(user);
	}

}
