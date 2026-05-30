package com.gymapp.gymapi.Controller;

import com.gymapp.gymapi.Constants.AppRulesConstants;
import com.gymapp.gymapi.dto.response.LeaderboardEntryDto;
import com.gymapp.gymapi.dto.response.LeaderboardResponse;
import com.gymapp.gymapi.model.enums.LeaderboardCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/leaderboards")
@Tag(name = "Leaderboards", description = "Live gym leaderboards refreshed every " +
        AppRulesConstants.lEADERBOARD_REFRESH_MINS + " minutes. " +
        "Results are cached in Redis for " + AppRulesConstants.LEADERBOARD_CACHE_TTL + " seconds.")
public class LeaderboardController {

    @GetMapping
    @Operation(
            summary = "Get all leaderboards",
            description = "Returns the top 10 entries for every category: BIKE, TREADMILL, RUNNING, CYCLING, STRENGTH.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All leaderboards retrieved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LeaderboardResponse.class)))
            })
    public ResponseEntity<List<LeaderboardResponse>> getAllLeaderboards() {
        List<LeaderboardResponse> boards = Arrays.stream(LeaderboardCategory.values())
                .map(this::buildStubBoard)
                .toList();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{category}")
    @Operation(
            summary = "Get leaderboard by category",
            description = "Returns the top entries for a specific category. Backed by the corresponding Redis key.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Leaderboard retrieved"),
                    @ApiResponse(responseCode = "400", description = "Invalid category. Valid values: BIKE, TREADMILL, RUNNING, CYCLING, STRENGTH")
            })
    public ResponseEntity<LeaderboardResponse> getLeaderboard(
            @Parameter(description = "Leaderboard category", example = "RUNNING",
                    schema = @Schema(allowableValues = {"BIKE", "TREADMILL", "RUNNING", "CYCLING", "STRENGTH"}))
            @PathVariable String category) {
        LeaderboardCategory cat = LeaderboardCategory.valueOf(category.toUpperCase());
        return ResponseEntity.ok(buildStubBoard(cat));
    }

    private LeaderboardResponse buildStubBoard(LeaderboardCategory cat) {
        return LeaderboardResponse.builder()
                .category(cat.name())
                .cacheKey(cat.getRedisKey())
                .lastRefreshed(Instant.now())
                .entries(List.of(
                        LeaderboardEntryDto.builder()
                                .rank(1).userId(UUID.randomUUID())
                                .displayName("DevPrinz").photoUrl("https://example.com/photos/devprinz.webp")
                                .score(150000).unit("metres")
                                .build(),
                        LeaderboardEntryDto.builder()
                                .rank(2).userId(UUID.randomUUID())
                                .displayName("Jane Doe").photoUrl("https://example.com/photos/jane.webp")
                                .score(142000).unit("metres")
                                .build()))
                .build();
    }
}
