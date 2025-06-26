package com.twylio.user_activity_tracker.controller;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import com.twylio.user_activity_tracker.model.UserEvent;
import com.twylio.user_activity_tracker.service.UserEventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/events")
public class UserEventController {

    private final UserEventService userEventService;

    public UserEventController(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void logEvent(@RequestBody @Valid UserEventRequest request) {
        userEventService.logEvent(request);
    }

    @GetMapping
    public List<UserEvent> getEvents(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) Instant startTime,
            @RequestParam(required = false) Instant endTime
    ) {
        return userEventService.getEvents(userId, eventType, startTime, endTime);
    }
}
