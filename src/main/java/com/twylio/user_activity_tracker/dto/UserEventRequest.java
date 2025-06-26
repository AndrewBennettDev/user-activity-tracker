package com.twylio.user_activity_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@NoArgsConstructor
public class UserEventRequest {

    @NotBlank
    @Getter
    @Setter
    private String userId;
    @NotBlank
    @Getter
    @Setter
    private String eventType;
    @NotNull
    @Getter
    @Setter
    private Instant timestamp;
    @Getter
    @Setter
    private Map<String, String> metadata;
}
