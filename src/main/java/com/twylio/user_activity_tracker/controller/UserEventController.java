package com.twylio.user_activity_tracker.controller;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import com.twylio.user_activity_tracker.models.UserEvent;
import com.twylio.user_activity_tracker.repository.UserEventRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/events")
public class UserEventController {

    private final UserEventRepository userEventRepository;

    public UserEventController(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void logEvent(@RequestBody @Valid UserEventRequest request) {
        UserEvent event = new UserEvent();
        event.setUserId(request.getUserId());
        event.setEventType(request.getEventType());
        event.setTimestamp(request.getTimestamp());
        event.setMetadata(request.getMetadata());

        userEventRepository.save(event);
    }

    @GetMapping
    public List<UserEvent> getEvents(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) Instant startTime,
            @RequestParam(required = false) Instant endTime
    ) {
        if (userId != null && eventType != null) {
            return userEventRepository.findByUserIdAndEventType(userId, eventType);
        } else if (userId != null) {
            return userEventRepository.findByUserId(userId);
        } else if (startTime != null && endTime != null) {
            return userEventRepository.findByTimestampBetween(startTime, endTime);
        } else {
            return userEventRepository.findAll();
        }
    }
}
