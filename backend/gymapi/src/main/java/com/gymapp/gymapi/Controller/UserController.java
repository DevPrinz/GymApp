package com.gymapp.gymapi.Controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymapp.gymapi.Constatnts.ApiConstants;
import com.gymapp.gymapi.Service.UserService;
import com.gymapp.gymapi.dto.request.UserUpdateRequest;
import com.gymapp.gymapi.dto.response.UserResponse;
import com.gymapp.gymapi.util.AuthUtil;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.USERS_PATH)
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final AuthUtil authUtil;	
	
	@PostMapping("/me")
	public ResponseEntity<UserResponse>syncUser(@RequestBody UserUpdateRequest request){
		UUID supabaseId = authUtil.getCurrentUserId();
		String email = authUtil.getCurrentUserEmail();
		UserResponse response = userService.upsertUser(supabaseId, email, request);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/me")
	public ResponseEntity<UserResponse>getProfile(){
		UUID supabaseId = authUtil.getCurrentUserId();
		return ResponseEntity.ok(userService.getBySupabaseId(supabaseId));
	}
	
//	@PostMapping("/create")
//	public ResponseEntity<UserResponse>createProfile(){
//		
//	}
	

}
