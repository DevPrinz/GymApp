package com.gymapp.gymapi.model;

import com.gymapp.gymapi.model.enums.SubscriptionTier;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "supabase_id", unique = true, nullable = false)
    private UUID supabaseId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "display_name")
    private String displayName;

    private String postcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_tier")
    @Builder.Default
    private SubscriptionTier subscriptionTier = SubscriptionTier.FREE;

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();
}