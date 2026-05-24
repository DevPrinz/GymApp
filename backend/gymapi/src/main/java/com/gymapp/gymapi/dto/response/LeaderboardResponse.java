package com.gymapp.gymapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Full leaderboard for a given category")
public class LeaderboardResponse {

    @Schema(description = "Leaderboard category", example = "RUNNING")
    private String category;

    @Schema(description = "Cache key backing this leaderboard", example = "leaderboard:running")
    private String cacheKey;

    @Schema(description = "Ordered list of entries (top to bottom)")
    private List<LeaderboardEntryDto> entries;

    @Schema(description = "When this leaderboard was last refreshed")
    private Instant lastRefreshed;
}
