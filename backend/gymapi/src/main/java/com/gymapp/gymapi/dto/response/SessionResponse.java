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
@Schema(description = "FTMS equipment session record")
public class SessionResponse {

    @Schema(description = "Unique session ID")
    private UUID id;

    @Schema(description = "ID of the user who logged the session")
    private UUID userId;

    @Schema(description = "Type of equipment used", example = "TREADMILL")
    private String equipmentType;

    @Schema(description = "Duration of the session in seconds", example = "1800")
    private int durationSeconds;

    @Schema(description = "Distance covered in metres", example = "5000")
    private double distanceMetres;

    @Schema(description = "Calories burned during the session", example = "350")
    private int caloriesBurned;

    @Schema(description = "Average heart rate in BPM", example = "145")
    private Integer avgHeartRateBpm;

    @Schema(description = "When the session was recorded")
    private Instant createdAt;
}
