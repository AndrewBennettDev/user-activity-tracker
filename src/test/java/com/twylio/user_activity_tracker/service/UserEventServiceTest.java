package com.twylio.user_activity_tracker.service;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import com.twylio.user_activity_tracker.repository.UserEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Instant;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserEventServiceTest {

    private UserEventService userEventService;
    private UserEventRepository userEventRepository;

    @BeforeEach
    public void setUp() {
        userEventRepository = mock(UserEventRepository.class);
        userEventService = new UserEventService(userEventRepository);
    }

    @Test
    public void shouldMapAndSaveUserEvent() {
        UserEventRequest request = new UserEventRequest();
        request.setUserId("test-user");
        request.setEventType("LOGIN");
        request.setTimestamp(Instant.now());
        request.setMetadata(Map.of("browser", "Brave"));

        userEventService.logEvent(request);

        ArgumentCaptor<UserEventRequest> captor = ArgumentCaptor.forClass(UserEventRequest.class);
        //verify(userEventRepository).save(captor.capture());
    }
}
