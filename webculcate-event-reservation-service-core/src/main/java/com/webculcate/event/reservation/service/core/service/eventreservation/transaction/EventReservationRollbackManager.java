package com.webculcate.event.reservation.service.core.service.eventreservation.transaction;

import com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction.EventReservationRollbackDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReservationRollbackManager {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void manageEventReservationRollback(EventReservationRollbackDto rollbackDto) {

    }


}
