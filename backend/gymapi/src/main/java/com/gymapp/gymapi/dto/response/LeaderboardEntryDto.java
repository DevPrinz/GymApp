package com.gymapp.gymapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "A single entry on a leaderboard")
public class LeaderboardEntryDto {

    @Schema(description = "User's rank on this leaderboard", example = "1")
    private int rank;

    @Schema(description = "User ID")
    private UUID userId;

    @Schema(description = "User's display name", example = "DevPrinz")
    private String displayName;

    @Schema(description = "Profile photo URL")
    private String photoUrl;

    @Schema(description = "The score or metric value (e.g. total distance in metres)", example = "150000")
    private double score;

    @Schema(description = "Human-readable unit for the score", example = "metres")
    private String unit;
}
