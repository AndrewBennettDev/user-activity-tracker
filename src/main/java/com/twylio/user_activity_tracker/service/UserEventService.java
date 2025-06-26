package com.twylio.user_activity_tracker.service;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import com.twylio.user_activity_tracker.model.UserEvent;
import com.twylio.user_activity_tracker.repository.UserEventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserEventService {

    private final UserEventRepository userEventRepository;

    public UserEventService(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }

    public void logEvent(UserEventRequest request) {
        UserEvent event = new UserEvent();
        event.setUserId(request.getUserId());
        event.setEventType(request.getEventType());
        event.setTimestamp(request.getTimestamp());
        event.setMetadata(request.getMetadata());

        userEventRepository.save(event);
    }

    public List<UserEvent> getEvents(String userId, String eventType, Instant startTime, Instant endTime) {
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
