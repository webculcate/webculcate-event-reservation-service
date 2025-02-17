package com.webculcate.event.reservation.service.core.repository.eventreservation;

import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventReservationRepository extends JpaRepository<ScheduledEventReservation, Long> {

    Optional<ScheduledEventReservation> findByScheduledEventReservationId(Long id);

}
