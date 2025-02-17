package com.webculcate.event.reservation.service.core.service.eventreservation.transaction;

import com.webculcate.event.reservation.service.core.constant.PaymentOperationType;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction.EventReservationRollbackDto;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateResponse;
import com.webculcate.event.reservation.service.core.service.payment.PaymentManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.NOT_AVAILABLE;
import static com.webculcate.event.reservation.service.core.utility.ServiceHelper.nullHandledExtraction;
import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReservationRollbackManager {

    private final CapacityReductionRollbackEventPublisher rollbackEventPublisher;

    private final PaymentManager paymentManager;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void manageEventReservationRollback(EventReservationRollbackDto rollbackDto) {
        if (nullHandledExtraction(() -> rollbackDto.getCapacityUpdateResponse().getSuccess()).orElse(false)) {
            CapacityUpdateResponse capacityUpdateResponse = rollbackDto.getCapacityUpdateResponse();
            Long scheduledEventId = capacityUpdateResponse.getScheduledEventId();
            Integer capacityAfterUpdate = capacityUpdateResponse.getCapacityAfterUpdate();
            rollbackEventPublisher.publishCapacityReductionRollbackEvent(scheduledEventId, capacityAfterUpdate);
        }

        if (nullHandledExtraction(() -> rollbackDto.getPaymentResponse().isSuccessful()).orElse(false)) {
            PaymentResponse paymentResponse = rollbackDto.getPaymentResponse();
            PaymentRequest request = PaymentRequest.builder()
                    .amount(paymentResponse.getPayment().getAmount())
                    .purchasedBy(nullHandledExtraction(
                            () -> paymentResponse.getPayment().getPurchasedBy().getUserId()
                    ).orElse(null))
                    .paymentOperation(PaymentOperationType.CREDIT)
                    .build();
            paymentManager.pay(request);
        }

    }


}
