package com.gymapp.gymapi.dto.request;

import com.gymapp.gymapi.model.enums.SubscriptionTier;

import lombok.Data;

@Data
public class UserCreateRequest {

	public String displayName;
	
	public String postCode;
	
	public String email;
	
	public String password;
	
	public  SubscriptionTier subTier;
}
