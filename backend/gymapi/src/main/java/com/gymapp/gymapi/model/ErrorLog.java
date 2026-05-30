package com.gymapp.gymapi.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

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
/**
 * An error table that logs server side error weitteb by the
 * global exception handler
 */
@Entity
@Table(
        name = TableNamesConstants.ERROR_LOGS,
        indexes = {
                @Index(name = "idx_error_logs_occurred_at", columnList = "occurred_at"),
                @Index(name = "idx_error_logs_trace_id", columnList = "trace_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Correlation id also returned to the client in the ApiError body. */
    @Column(name = "trace_id", length = 36)
    private String traceId;

    @Column(name = "status_code")
    private Integer statusCode;

    /** Application error code, e.g. EXTERNAL_SERVICE_ERROR. */
    @Column(name = "error_code", length = 64)
    private String errorCode;

    @Column(name = "http_method", length = 10)
    private String httpMethod;

    private String path;

    /** Supabase user UUID if the request was authenticated. */
    @Column(name = "user_id", columnDefinition = "uuid")
    private UUID userId;

    @Column(length = 2000)
    private String message;

    @Column(name = "exception_class")
    private String exceptionClass;

    @Column(name = "stack_trace", columnDefinition = "text")
    private String stackTrace;

    @CreationTimestamp
    @Column(name = "occurred_at", updatable = false)
    private Instant occurredAt;
}