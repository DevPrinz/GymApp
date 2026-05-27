package com.gymapp.gymapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Request body for logging a food/meal entry")

public class NutritionLogRequest {

    @NotBlank
    @Schema(description = "Name of the food item", example = "Chicken breast")
    private String foodName;

    @Schema(description = "Barcode of the product (used to look up Open Food Facts)", example = "5000159407236")
    private String barcode;

    @Positive
    @Schema(description = "Amount consumed in grams", example = "150")
    private double grams;

    @Schema(description = "Meal type", example = "LUNCH", allowableValues = {"BREAKFAST", "LUNCH", "DINNER", "SNACK"})
    private String mealType;
}
