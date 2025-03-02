package com.webculcate.event.reservation.service.core.service.eventreservation.transaction;

import com.webculcate.event.reservation.service.core.constant.PaymentOperationType;
import com.webculcate.event.reservation.service.core.exception.eventreservation.EventReservationCreationFailedException;
import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidCapacityException;
import com.webculcate.event.reservation.service.core.exception.eventreservation.PaymentFailedException;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction.EventReservationRollbackDto;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;
import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateResponse;
import com.webculcate.event.reservation.service.core.repository.eventreservation.EventReservationRepository;
import com.webculcate.event.reservation.service.core.service.eventreservation.factory.IEventReservationFactory;
import com.webculcate.event.reservation.service.core.service.external.event.EventServiceExt;
import com.webculcate.event.reservation.service.core.service.payment.PaymentManager;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.webculcate.event.reservation.service.core.constant.ScheduledEventReservationStatus.CONFIRMED;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.STRING_UNDERSCORE;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.ZERO_LONG;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReservationTransactionService {

    private final EventReservationRepository repository;

    private final EventServiceExt eventServiceExt;

    private final PaymentManager paymentManager;

    private final IEventReservationFactory factory;

    private final ApplicationEventPublisher publisher;

    private final EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> createEventReservationTransaction(EventReservationCreationRequest request, Integer capacity) {
        EventReservationRollbackDto rollbackDto = new EventReservationRollbackDto();
        try {
            CapacityUpdateResponse reductionResponse = eventServiceExt.reduceCapacity(request.getScheduledEventId(), capacity);
            if (reductionResponse.hasFailed())
                throw new InvalidCapacityException();
            else
                rollbackDto.setCapacityUpdateResponse(reductionResponse);
            List<ScheduledEventReservation> reservations = generateAndSaveEventReservations(request);
            PaymentResponse paymentResponse = paymentManager.adaptAndPay(request, PaymentOperationType.DEBIT);
            if (paymentResponse.isNotSuccessful()) {
                publisher.publishEvent(rollbackDto);
                throw new PaymentFailedException();
            } else {
                rollbackDto.setPaymentResponse(paymentResponse);
            }
            reservations = confirmEventReservations(reservations, paymentResponse);
            return reservations;
        } catch (Exception exception) {
            log.error("Exception : ", exception);
            publisher.publishEvent(rollbackDto);
            throw new EventReservationCreationFailedException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> generateAndSaveEventReservations(EventReservationCreationRequest request) {
        List<ScheduledEventReservation> reservations = generateEventReservation(request.getCustomerIds(), request.getScheduledEventId());
        repository.saveAll(reservations);
        return reservations;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> generateEventReservation(Set<Long> customerIds, Long scheduledEventId) {
        return customerIds.stream()
                .map(customerId -> generateEventReservation(customerId, scheduledEventId))
                .toList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ScheduledEventReservation generateEventReservation(Long customerId, Long scheduledEventId) {
        return factory.generateScheduledEventReservation(customerId, scheduledEventId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> confirmEventReservations(List<ScheduledEventReservation> reservations, PaymentResponse paymentResponse) {
        reservations.forEach(reservation -> {
            reservation.setStatus(CONFIRMED);
            reservation.setConfirmationKey(
                    String.join(STRING_UNDERSCORE,
                            reservation.getScheduledEventId().toString(),
                            reservation.getCustomerId().toString(),
                            CONFIRMED.name())
            );
            reservation.setPayment(new Payment(paymentResponse.getPayment().getPaymentId(), ZERO_LONG));
        });
        reservations = repository.saveAllAndFlush(reservations);
        reservations.forEach(entityManager::refresh);
        return reservations;
    }

    @Transactional(readOnly = true)
    public List<ScheduledEventReservation> getEventReservationList(EventReservationPaginationRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<ScheduledEventReservation> resultList = repository.findAll(pageRequest);
        return resultList.toList();
    }

}
