package com.gymapp.gymapi.Controller;

import com.gymapp.gymapi.Constants.ApiConstants;
import com.gymapp.gymapi.Constants.AppRulesConstants;
import com.gymapp.gymapi.dto.request.CoachMessageRequest;
import com.gymapp.gymapi.dto.response.CoachMessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiConstants.AI_COACH_PATH)
@Tag(name = "AI Coach", description = "GPT-powered personal coaching. Free users get " +
        AppRulesConstants.FREE_AI_MESSAGES_PER_DAY +
        " messages per day. The coach uses up to " + AppRulesConstants.AI_COACH_WEEKS_HISTORY +
        " weeks of training history for context.")
public class AiCoachController {

    @PostMapping("/message")
    @Operation(
            summary = "Send a message to the AI coach",
            description = "Forwards the user's message to " + AppRulesConstants.OPENAPI_MODEL +
                    " with recent training history as context. Rate-limited to " +
                    AppRulesConstants.FREE_AI_MESSAGES_PER_DAY + " messages/day for free accounts.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Coach reply received",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CoachMessageResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Message too long or blank"),
                    @ApiResponse(responseCode = "429", description = "Daily AI message limit reached")
            })
    public ResponseEntity<CoachMessageResponse> sendMessage(@Valid @RequestBody CoachMessageRequest request) {
        CoachMessageResponse stub = CoachMessageResponse.builder()
                .id(UUID.randomUUID())
                .userMessage(request.getMessage())
                .coachReply("Based on your recent sessions, I recommend a tempo run on Wednesday followed by a rest day before your long run on Saturday.")
                .model(AppRulesConstants.OPENAPI_MODEL)
                .remainingMessagesToday(AppRulesConstants.FREE_AI_MESSAGES_PER_DAY - 1)
                .createdAt(Instant.now())
                .build();
        return ResponseEntity.ok(stub);
    }

    @GetMapping("/history")
    @Operation(
            summary = "Get coaching history",
            description = "Returns the last " + AppRulesConstants.AI_COACH_WEEKS_HISTORY +
                    " weeks of AI coach conversation history for the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "History retrieved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CoachMessageResponse.class)))
            })
    public ResponseEntity<List<CoachMessageResponse>> getHistory(
            @Parameter(description = "Max number of messages to return (default 50)")
            @RequestParam(defaultValue = "50") int limit) {
        List<CoachMessageResponse> stub = List.of(
                CoachMessageResponse.builder()
                        .id(UUID.randomUUID())
                        .userMessage("How can I improve my cycling endurance?")
                        .coachReply("Focus on zone-2 rides lasting 60-90 minutes, 3 times per week.")
                        .model(AppRulesConstants.OPENAPI_MODEL)
                        .remainingMessagesToday(9)
                        .createdAt(Instant.now().minusSeconds(86400))
                        .build());
        return ResponseEntity.ok(stub);
    }
}
