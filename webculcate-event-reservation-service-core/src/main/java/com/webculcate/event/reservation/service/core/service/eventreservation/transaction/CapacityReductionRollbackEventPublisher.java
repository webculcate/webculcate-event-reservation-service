package com.webculcate.event.reservation.service.core.service.eventreservation.transaction;

import com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction.CapacityReductionRollbackEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK;

@Slf4j
@Service
@RequiredArgsConstructor
public class CapacityReductionRollbackEventPublisher {

    private final KafkaTemplate<String, CapacityReductionRollbackEvent> publisherService;

    public void publishCapacityReductionRollbackEvent(CapacityReductionRollbackEvent capacityReductionRollbackEvent) {
        publisherService.send(WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK, capacityReductionRollbackEvent);
    }

    public void publishCapacityReductionRollbackEvent(Long scheduledEventId, Integer creditCapacity) {
        publishCapacityReductionRollbackEvent(new CapacityReductionRollbackEvent(
                scheduledEventId,
                creditCapacity
        ));
    }

}
