package com.gymapp.gymapi.dto.request;

import lombok.Data;

@Data
public class UserSyncRequest {
    private String displayName;
    private String postcode;
}