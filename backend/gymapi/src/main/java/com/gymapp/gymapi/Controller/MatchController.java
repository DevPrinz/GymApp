package com.gymapp.gymapi.Controller;

import com.gymapp.gymapi.Constants.ApiConstants;
import com.gymapp.gymapi.Constants.AppRulesConstants;
import com.gymapp.gymapi.dto.request.SwipeRequest;
import com.gymapp.gymapi.dto.response.MatchResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(ApiConstants.MATCHES_PATH)
@Tag(name = "Matches", description = "Gym-buddy discovery and matching. Free users get " +
        AppRulesConstants.FREE_SWIPER_PER_DAY + " swipes per day.")
public class MatchController {

    @GetMapping
    @Operation(
            summary = "List matches",
            description = "Returns all mutual matches for the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Matches retrieved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MatchResponse.class)))
            })
    public ResponseEntity<List<MatchResponse>> getMatches() {
        List<MatchResponse> stub = List.of(
                MatchResponse.builder()
                        .id(UUID.randomUUID())
                        .matchedUserId(UUID.randomUUID())
                        .matchedUserName("Jane Doe")
                        .matchedUserPhotoUrl("https://example.com/photos/jane.webp")
                        .matchedAt(Instant.now())
                        .build());
        return ResponseEntity.ok(stub);
    }

    @PostMapping("/swipe")
    @Operation(
            summary = "Swipe on a user",
            description = "Swipe left or right on another user. Free accounts are capped at " +
                    AppRulesConstants.FREE_SWIPER_PER_DAY + " swipes per day (tracked via Redis).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Swipe recorded; body indicates if a match was made"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "429", description = "Daily swipe limit reached")
            })
    public ResponseEntity<Map<String, Object>> swipe(@Valid @RequestBody SwipeRequest request) {
        Map<String, Object> stub = Map.of(
                "matched", false,
                "swipesRemainingToday", AppRulesConstants.FREE_SWIPER_PER_DAY - 1,
                "targetUserId", request.getTargetUserId(),
                "direction", request.getDirection()
        );
        return ResponseEntity.status(HttpStatus.OK).body(stub);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Unmatch a user",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Unmatched successfully"),
                    @ApiResponse(responseCode = "404", description = "Match not found")
            })
    public ResponseEntity<Void> unmatch(
            @Parameter(description = "Match UUID") @PathVariable UUID id) {
        return ResponseEntity.noContent().build();
    }
}
