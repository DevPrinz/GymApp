package com.gymapp.gymapi.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.gymapp.gymapi.Constants.TableNamesConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * Table to store a local cache of the exercise catalogue
 * gotten from the wger api and displayed to the front end through redis cache
 * { wgerId} is the id of the exercise in the (self-hosted) Wger instance.
 */
@Entity
@Table(
		name = TableNamesConstants.EXERCISE,
		indexes = @Index(name = "idx_exercises_wger_id", columnList = "wger_id", unique = true)
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Unique id from wger instance
	@Column(name = "wger_id", unique = true)
	private Integer wgerId;
	
	@Column(nullable = false)
	private String name;
	
	private String category;
	
	@Column(name = "primary_muscles")
	private String primaryMuscles;
	
	@Column(name = "secondary_muscles")
	private String secondaryMuscles;
	
	private String equiptment;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "last_synced_at")
	private Instant lastSyncedAt;
	
	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private Instant createdAt;
	
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;
}
