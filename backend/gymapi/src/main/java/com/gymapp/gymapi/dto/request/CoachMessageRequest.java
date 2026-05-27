package com.gymapp.gymapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Request body for sending a message to the AI coach")
public class CoachMessageRequest {

    @NotBlank
    @Size(max = 1000)
    @Schema(description = "The user's message or question for the AI coach",
            example = "How should I structure my training this week to improve my 5k time?")
    private String message;
    
    
}
