package com.twylio.user_activity_tracker.controller;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import com.twylio.user_activity_tracker.kafka.UserEventKafkaProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka/events")
public class KafkaEventController {

    private final UserEventKafkaProducer producer;

    public KafkaEventController(UserEventKafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void publishEvent(@RequestBody @Valid UserEventRequest request) {
        producer.sendEvent(request);
    }
}
