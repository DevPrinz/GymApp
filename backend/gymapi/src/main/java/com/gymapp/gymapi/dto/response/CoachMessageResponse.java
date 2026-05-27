package com.gymapp.gymapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "AI coach reply to the user's message")
public class CoachMessageResponse {

    @Schema(description = "Unique message ID")
    private UUID id;

    @Schema(description = "The user's original message")
    private String userMessage;

    @Schema(description = "The AI coach's reply")
    private String coachReply;

    @Schema(description = "AI model that generated the reply", example = "GPT-4o")
    private String model;

    @Schema(description = "Number of AI messages remaining today")
    private int remainingMessagesToday;

    @Schema(description = "When the message was sent")
    private Instant createdAt;
}
