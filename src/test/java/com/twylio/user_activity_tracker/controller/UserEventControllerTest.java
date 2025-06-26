package com.twylio.user_activity_tracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twylio.user_activity_tracker.dto.UserEventRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(controllers = UserEventController.class)
public class UserEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserEventController userEventController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnCreatedWhenPostingValidEvent() throws Exception {
        UserEventRequest request = new UserEventRequest();
        request.setUserId("test-user");
        request.setEventType("SMS_SENT");
        request.setTimestamp(Instant.now());
        request.setMetadata(Map.of("key", "value"));

        mockMvc.perform(post("/events")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
