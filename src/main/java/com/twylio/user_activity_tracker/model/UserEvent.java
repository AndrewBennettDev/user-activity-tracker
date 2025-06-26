package com.twylio.user_activity_tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Entity
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String eventType;
    @Getter
    @Setter
    private Instant timestamp;

    @ElementCollection
    @Getter
    @Setter
    private Map<String, String> metadata;



}
