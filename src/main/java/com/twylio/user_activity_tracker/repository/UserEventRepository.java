package com.twylio.user_activity_tracker.repository;

import com.twylio.user_activity_tracker.models.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface UserEventRepository  extends JpaRepository<UserEvent, Long> {

    List<UserEvent> findByUserId(String userId);
    List<UserEvent> findByUserIdAndEventType(String userId, String eventType);

    @Query("SELECT e FROM UserEvent e WHERE e.timestamp BETWEEN :start AND :end")
    List<UserEvent> findByTimestampBetween(@Param("start")Instant start, @Param("end") Instant end);


}
