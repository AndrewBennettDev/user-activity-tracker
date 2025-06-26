package com.twylio.user_activity_tracker.repository;

import com.twylio.user_activity_tracker.model.UserEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserEventRepositoryTest {

    @Autowired
    private UserEventRepository repository;

    @Test
    public void shouldSaveAndQueryEventsByUserId() {
        UserEvent event1 = new UserEvent(null, "Alice", "SMS_SENT", Instant.now(), Map.of("status", "delivered"));
        UserEvent event2 = new UserEvent(null, "Bob", "LOGIN", Instant.now(), Map.of());
        UserEvent event3 = new UserEvent(null, "Alice", "CALL_PLACED", Instant.now(), Map.of());

        repository.saveAll(List.of(event1, event2, event3));

        List<UserEvent> results = repository.findByUserId("Alice");

        assertThat(results).hasSize(2);
        assertThat(results).allMatch(e -> e.getUserId().equals("Alice"));
    }
}
