package com.gymapp.gymapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Request body for swiping on another user")
public class SwipeRequest {

    @NotNull
    @Schema(description = "ID of the user being swiped on")
    private UUID targetUserId;

    @NotNull
    @Schema(description = "Direction of the swipe", example = "RIGHT", allowableValues = {"LEFT", "RIGHT"})
    private String direction;
}
