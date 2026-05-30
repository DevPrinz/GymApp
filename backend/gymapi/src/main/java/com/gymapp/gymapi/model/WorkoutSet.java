package com.gymapp.gymapi.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.gymapp.gymapi.Constants.TableNamesConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A single logged set within a {@link WorkoutSession}.
 * <p>
 * {@code exerciseId} references {@link Exercise#getId()} when known, but is nullable so a
 * set can still be logged for an exercise that has not been seeded yet. {@code exerciseName}
 * is always stored as a snapshot so history stays readable even if the catalogue changes.
 */
@Entity
@Table(
		name = TableNamesConstants.WORKOUT_SET,
		indexes = @Index(name = "idx_sets_session_id", columnList = "session_id", unique = true)
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "session_id", nullable = false)
	private WorkoutSession session;
	
	@Column(name = "exercise_id")
	private Long exerciseId;
	
	@Column(name = "exercise_name", nullable = false)
	private String exerciseName;
	
	@Column(name = "weight_kg", precision = 6, scale = 2)
	private BigDecimal weightKg;
	
	@Column(name = "reps", nullable = false)
	private Integer reps;
	
	@Column(precision = 3, scale = 1)
	private BigDecimal rpe;
	
	@Column(name = "set_number", nullable = false)
	private Integer setNumber;
	
	@Column(name = "rest_seconds")
	private Integer restSeconds;
	
	@CreationTimestamp
	@Column(name = "logged_at", updatable = false)
	private Instant loggedAt;
}
