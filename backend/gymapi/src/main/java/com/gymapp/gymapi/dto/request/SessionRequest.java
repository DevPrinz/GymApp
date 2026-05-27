package com.gymapp.gymapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Request body for logging a new FTMS equipment session")
public class SessionRequest {

    @NotNull
    @Schema(description = "Type of equipment used", example = "TREADMILL",
            allowableValues = {"TREADMILL", "BIKE", "ROWER", "CROSS_TRAINER"})
    private String equipmentType;

    @Positive
    @Schema(description = "Duration of the session in seconds", example = "1800")
    private int durationSeconds;

    @Positive
    @Schema(description = "Distance covered in metres", example = "5000")
    private double distanceMetres;

    @Schema(description = "Calories burned during the session", example = "350")
    private int caloriesBurned;

    @Schema(description = "Average heart rate during session in BPM", example = "145")
    private Integer avgHeartRateBpm;
}
