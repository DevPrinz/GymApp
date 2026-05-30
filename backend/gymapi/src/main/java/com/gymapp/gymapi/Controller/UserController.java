package com.gymapp.gymapi.Controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymapp.gymapi.Constants.ApiConstants;
import com.gymapp.gymapi.Service.UserService;
import com.gymapp.gymapi.dto.request.UserUpdateRequest;
import com.gymapp.gymapi.dto.response.UserResponse;
import com.gymapp.gymapi.util.AuthUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.USERS_PATH)
@RequiredArgsConstructor
@Tag(name = "Users", description = "User proile management")
public class UserController {
	private final UserService userService;
	private final AuthUtil authUtil;	
	
	@PostMapping("/me")
	@Operation(summary = "sync user after login", description = "Creates proile row on first login, updates on subsequest login")
	public ResponseEntity<UserResponse>syncUser(@RequestBody UserUpdateRequest request){
		UUID supabaseId = authUtil.getCurrentUserId();
		String email = authUtil.getCurrentUserEmail();
//		UserResponse response = userService.upsertUser(supabaseId, email, request);
		return ResponseEntity.ok(userService.upsertUser(supabaseId, email, request));
	}
	
	@GetMapping("/me")
	@Operation(summary  = "Get current user profile")
	public ResponseEntity<UserResponse>getProfile(){
		UUID supabaseId = authUtil.getCurrentUserId();
		return ResponseEntity.ok(userService.getBySupabaseId(supabaseId));
	}
	
	@PutMapping("/me")
	@Operation(summary = "Update user profile", description = "Updates fitness goals"
			+ ", body metrics, gym preferences. Only sends changed fields.")
	public ResponseEntity<UserResponse>updateProfile(@RequestBody UserUpdateRequest request){
		UUID supabaseId = authUtil.getCurrentUserId();
		return ResponseEntity.ok(userService.updateProfile(supabaseId, request));
		
	}
	

}
