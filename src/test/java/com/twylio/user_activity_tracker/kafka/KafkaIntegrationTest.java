package com.twylio.user_activity_tracker.kafka;

import com.twylio.user_activity_tracker.dto.UserEventRequest;
import com.twylio.user_activity_tracker.repository.UserEventRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KafkaIntegrationTest {

    private static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.1"));

    static {
        kafka.start();
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private KafkaTemplate<String, UserEventRequest> kafkaTemplate;

    @Test
    @Disabled
    public void shouldConsumeKafkaEventAndSaveToDatabase() throws Exception {

        UserEventRequest request = new UserEventRequest();
        request.setUserId("kafka-test");
        request.setEventType("SMS_RECEIVED");
        request.setTimestamp(Instant.now());
        request.setMetadata(Map.of("from", "+1234567890"));

        kafkaTemplate.send("user-events", request);

        Awaitility.await()
                .atMost(Duration.ofSeconds(5))
                .untilAsserted(() -> {
                    var results = userEventRepository.findByUserId("kafka-test");
                    assertThat(results).isNotEmpty();
                });

    }
}
