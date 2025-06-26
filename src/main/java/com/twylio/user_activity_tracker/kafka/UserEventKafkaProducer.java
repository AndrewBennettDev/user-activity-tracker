package com.twylio.user_activity_tracker.kafka;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventKafkaProducer {

    private final KafkaTemplate<String, UserEventRequest> kafkaTemplate;

    public UserEventKafkaProducer(KafkaTemplate<String, UserEventRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(UserEventRequest userEventRequest) {
        kafkaTemplate.send("user_activity_tracker", userEventRequest);
        System.out.println("Produced event to kafka: " + userEventRequest.getEventType());
    }
}
