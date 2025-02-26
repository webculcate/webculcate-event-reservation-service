package com.webculcate.event.reservation.service.core.model.entity.eventreservation;

import com.webculcate.event.reservation.service.core.constant.ScheduledEventReservationStatus;
import com.webculcate.event.reservation.service.core.model.entity.embedded.TimeLog;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.*;

@Data
@Builder
@Entity
@Table(
        name = SCHEDULED_EVENT_RESERVATION_TABLE_NAME,
        indexes = @Index(
                columnList = SCHEDULED_EVENT_ID_COLUMN_NAME,
                name = SCHEDULED_EVENT_ID_INDEX_NAME
        )
)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ScheduledEventReservation {

    @Id
    @SequenceGenerator(
            name = SCHEDULED_EVENT_RESERVATION_SEQUENCE_NAME,
            sequenceName = SCHEDULED_EVENT_RESERVATION_SEQUENCE_NAME,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SCHEDULED_EVENT_RESERVATION_SEQUENCE_NAME
    )
    private Long scheduledEventReservationId;

    @Column(nullable = false)
    private Long scheduledEventId;

    @Column(nullable = false)
    private ScheduledEventReservationStatus status;

    @ManyToOne
    @JoinColumn(
            name = PAYMENT_FOREIGN_KEY,
            referencedColumnName = "paymentId"
    )
    private Payment payment;

    @Column(nullable = false)
    private Long customerId;

    /*
     * to avoid duplicate confirmed entries for a customer
     */
    @Column(unique = true)
    private String confirmationKey;

    @Embedded
    private TimeLog timeLog;

    @Version
    private Long version;

}
