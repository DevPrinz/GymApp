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
@Schema(description = "A mutual match between two users")
public class MatchResponse {

    @Schema(description = "Unique match ID")
    private UUID id;

    @Schema(description = "ID of the other matched user")
    private UUID matchedUserId;

    @Schema(description = "Display name of the matched user")
    private String matchedUserName;

    @Schema(description = "Profile photo URL of the matched user")
    private String matchedUserPhotoUrl;

    @Schema(description = "When the match was created")
    private Instant matchedAt;
}
