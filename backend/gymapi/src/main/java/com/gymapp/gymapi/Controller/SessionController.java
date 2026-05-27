package com.gymapp.gymapi.Controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymapp.gymapi.Constatnts.ApiConstants;
import com.gymapp.gymapi.dto.request.SessionRequest;
import com.gymapp.gymapi.dto.response.SessionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.SESSIONS_PATH)
@Tag(name = "Sessions", description = "FTMS equipment session tracking")
public class SessionController {

    @GetMapping
    @Operation(
            summary = "List sessions",
            description = "Returns all FTMS equipment sessions logged by the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sessions retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SessionResponse.class)))
            })
    public ResponseEntity<List<SessionResponse>> getSessions() {
        List<SessionResponse> stub = List.of(
                SessionResponse.builder()
                        .id(UUID.randomUUID())
                        .userId(UUID.randomUUID())
                        .equipmentType("TREADMILL")
                        .durationSeconds(1800)
                        .distanceMetres(5000)
                        .caloriesBurned(350)
                        .avgHeartRateBpm(145)
                        .createdAt(Instant.now())
                        .build());
        return ResponseEntity.ok(stub);
    }

    @PostMapping
    @Operation(
            summary = "Log a session",
            description = "Records a new FTMS equipment session for the authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Session created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request body")
            })
    public ResponseEntity<SessionResponse> createSession(@Valid @RequestBody SessionRequest request) {
        SessionResponse stub = SessionResponse.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .equipmentType(request.getEquipmentType())
                .durationSeconds(request.getDurationSeconds())
                .distanceMetres(request.getDistanceMetres())
                .caloriesBurned(request.getCaloriesBurned())
                .avgHeartRateBpm(request.getAvgHeartRateBpm())
                .createdAt(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(stub);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get session by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Session found"),
                    @ApiResponse(responseCode = "404", description = "Session not found")
            })
    public ResponseEntity<SessionResponse> getSession(
            @Parameter(description = "Session UUID") @PathVariable UUID id) {
        SessionResponse stub = SessionResponse.builder()
                .id(id)
                .userId(UUID.randomUUID())
                .equipmentType("BIKE")
                .durationSeconds(3600)
                .distanceMetres(25000)
                .caloriesBurned(600)
                .createdAt(Instant.now())
                .build();
        return ResponseEntity.ok(stub);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a session",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Session deleted"),
                    @ApiResponse(responseCode = "404", description = "Session not found")
            })
    public ResponseEntity<Void> deleteSession(
            @Parameter(description = "Session UUID") @PathVariable UUID id) {
        return ResponseEntity.noContent().build();
    }
}
