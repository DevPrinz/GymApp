package com.gymapp.gymapi.dto.response;


import com.gymapp.gymapi.model.User;
import com.gymapp.gymapi.model.enums.SubscriptionTier;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class UserResponse {

    private UUID id;
    private String email;
    private String displayName;
    private String postcode;
    private SubscriptionTier subscriptionTier;

    // Converts a User entity into a UserResponse 

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .displayName(user.getDisplayName())
                .postcode(user.getPostcode())
                .subscriptionTier(user.getSubscriptionTier())
                .build();
    }
}