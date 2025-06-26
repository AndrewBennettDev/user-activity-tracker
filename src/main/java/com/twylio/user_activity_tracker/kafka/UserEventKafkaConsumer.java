package com.twylio.user_activity_tracker.kafka;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import com.twylio.user_activity_tracker.model.UserEvent;
import com.twylio.user_activity_tracker.repository.UserEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventKafkaConsumer {

    private final UserEventRepository userEventRepository;

    public UserEventKafkaConsumer(UserEventRepository userEventRepository) {
        this.userEventRepository = userEventRepository;
    }

    @KafkaListener(topics = "user-events", groupId = "user-event-group")
    public void consume(UserEventRequest request) {
        System.out.println("Received UserEvent : " + request.getEventType());

        UserEvent event = new UserEvent();

        event.setUserId(request.getUserId());
        event.setEventType(request.getEventType());
        event.setTimestamp(request.getTimestamp());
        event.setMetadata(request.getMetadata());

        userEventRepository.save(event);
    }
}
