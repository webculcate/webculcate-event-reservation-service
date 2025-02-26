package com.webculcate.event.reservation.service.core.service.eventreservation.transaction;

import com.webculcate.event.reservation.service.core.constant.PaymentOperationType;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction.EventReservationRollbackDto;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateResponse;
import com.webculcate.event.reservation.service.core.service.general.GeneralAsynchronousService;
import com.webculcate.event.reservation.service.core.service.payment.PaymentManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.webculcate.event.reservation.service.core.utility.ServiceHelper.nullHandledExtraction;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReservationRollbackManager {

    private final CapacityReductionRollbackEventPublisher rollbackEventPublisher;

    private final PaymentManager paymentManager;

    private final GeneralAsynchronousService generalAsynchronousService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void manageEventReservationRollback(EventReservationRollbackDto rollbackDto) {
        List<CompletableFuture> completableFutureList = new ArrayList<>();
        if (nullHandledExtraction(() -> rollbackDto.getCapacityUpdateResponse().getSuccess()).orElse(false)) {
            Function<EventReservationRollbackDto, Boolean> capacityUpdateFunction = this::manageCapacityUpdateRollback;
            CompletableFuture<Boolean> capacityUpdateCompletableFuture = generalAsynchronousService.assignTask(capacityUpdateFunction, rollbackDto);
            completableFutureList.add(capacityUpdateCompletableFuture);
        }
        if (nullHandledExtraction(() -> rollbackDto.getPaymentResponse().isSuccessful()).orElse(false)) {
            Function<EventReservationRollbackDto, PaymentResponse> paymentUpdateFunction = this::managePaymentUpdateRollback;
            CompletableFuture<PaymentResponse> paymentResponseCompletableFuture = generalAsynchronousService.assignTask(paymentUpdateFunction, rollbackDto);
            completableFutureList.add(paymentResponseCompletableFuture);
        }
        completableFutureList.forEach(element -> {
            try {
                element.get();
            } catch (Exception exception) {
                log.error("Exception : ", exception);
            }
        });
    }

    private PaymentResponse managePaymentUpdateRollback(EventReservationRollbackDto rollbackDto) {
        PaymentResponse paymentResponse = rollbackDto.getPaymentResponse();
        PaymentRequest request = PaymentRequest.builder()
                .amount(paymentResponse.getPayment().getAmount())
                .purchasedBy(nullHandledExtraction(
                        () -> paymentResponse.getPayment().getPurchasedBy().getUserId()
                ).orElse(null))
                .paymentOperation(PaymentOperationType.CREDIT)
                .build();
        return paymentManager.pay(request);
    }

    private Boolean manageCapacityUpdateRollback(EventReservationRollbackDto rollbackDto) {
        Boolean result = false;
        CapacityUpdateResponse capacityUpdateResponse = rollbackDto.getCapacityUpdateResponse();
        Long scheduledEventId = capacityUpdateResponse.getScheduledEventId();
        Integer capacityDifference = capacityUpdateResponse.getCapacityBeforeUpdate() - capacityUpdateResponse.getCapacityAfterUpdate();
        rollbackEventPublisher.publishCapacityReductionRollbackEvent(scheduledEventId, capacityDifference);
        result = true;
        return result;
    }


}
