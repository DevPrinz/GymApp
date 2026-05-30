package com.gymapp.gymapi.Controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymapp.gymapi.Constants.ApiConstants;
import com.gymapp.gymapi.dto.request.NutritionLogRequest;
import com.gymapp.gymapi.dto.response.NutritionLogResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.NUTRITION_PATH)
@Tag(name = "Nutrition", description = "Nutrition logging powered by the Open Food Facts database (" +
        ApiConstants.FOOD_FACTS_URL + ")")
public class NutritionController {

    @GetMapping
    @Operation(
            summary = "Get nutrition logs",
            description = "Returns the authenticated user's nutrition log entries, optionally filtered by date.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logs retrieved",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NutritionLogResponse.class)))
            })
    public ResponseEntity<List<NutritionLogResponse>> getLogs(
            @Parameter(description = "Filter by date (ISO-8601, e.g. 2026-05-20)")
            @RequestParam(required = false) String date) {
        List<NutritionLogResponse> stub = List.of(
                NutritionLogResponse.builder()
                        .id(UUID.randomUUID())
                        .foodName("Chicken breast")
                        .grams(150)
                        .calories(248)
                        .proteinG(46.5)
                        .carbsG(0)
                        .fatG(5.4)
                        .mealType("LUNCH")
                        .loggedAt(Instant.now())
                        .build());
        return ResponseEntity.ok(stub);
    }

    @PostMapping
    @Operation(
            summary = "Log a food item",
            description = "Adds a food entry to the user's nutrition log. If a barcode is provided, " +
                    "macros are fetched from Open Food Facts and cached for " +
                    "30 days.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Entry logged"),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            })
    public ResponseEntity<NutritionLogResponse> logFood(@Valid @RequestBody NutritionLogRequest request) {
        NutritionLogResponse stub = NutritionLogResponse.builder()
                .id(UUID.randomUUID())
                .foodName(request.getFoodName())
                .grams(request.getGrams())
                .calories(request.getGrams() * 1.65)
                .proteinG(request.getGrams() * 0.31)
                .carbsG(0)
                .fatG(request.getGrams() * 0.036)
                .mealType(request.getMealType())
                .loggedAt(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(stub);
    }

    @GetMapping("/lookup/{barcode}")
    @Operation(
            summary = "Look up a food product by barcode",
            description = "Fetches nutritional data from Open Food Facts for the given product barcode. " +
                    "Results are cached for 30 days.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product found"),
                    @ApiResponse(responseCode = "404", description = "Product not found in Open Food Facts")
            })
    public ResponseEntity<Map<String, Object>> lookupProduct(
            @Parameter(description = "Product barcode (EAN-13 or similar)", example = "5000159407236")
            @PathVariable String barcode) {
        Map<String, Object> stub = Map.of(
                "barcode", barcode,
                "productName", "Example Product",
                "caloriesPer100g", 165,
                "proteinPer100g", 31,
                "carbsPer100g", 0,
                "fatPer100g", 3.6,
                "source", ApiConstants.FOOD_FACTS_URL + barcode
        );
        return ResponseEntity.ok(stub);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a nutrition log entry",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Entry deleted"),
                    @ApiResponse(responseCode = "404", description = "Entry not found")
            })
    public ResponseEntity<Void> deleteLog(
            @Parameter(description = "Log entry UUID") @PathVariable UUID id) {
        return ResponseEntity.noContent().build();
    }
}
