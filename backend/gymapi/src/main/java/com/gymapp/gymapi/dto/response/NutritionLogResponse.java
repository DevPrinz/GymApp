package com.gymapp.gymapi.dto.response;

import java.time.Instant;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "A logged nutrition entry")
public class NutritionLogResponse {

    @Schema(description = "Unique log entry ID")
    private UUID id;

    @Schema(description = "Name of the food item", example = "Chicken breast")
    private String foodName;

    @Schema(description = "Amount consumed in grams", example = "150")
    private double grams;

    @Schema(description = "Calories in this serving", example = "248")
    private double calories;

    @Schema(description = "Protein in grams", example = "46.5")
    private double proteinG;

    @Schema(description = "Carbohydrates in grams", example = "0")
    private double carbsG;

    @Schema(description = "Fat in grams", example = "5.4")
    private double fatG;

    @Schema(description = "Meal type", example = "LUNCH")
    private String mealType;

    @Schema(description = "When this entry was logged")
    private Instant loggedAt;
}
