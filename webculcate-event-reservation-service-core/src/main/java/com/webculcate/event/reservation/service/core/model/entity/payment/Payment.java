package com.webculcate.event.reservation.service.core.model.entity.payment;

import com.webculcate.event.reservation.service.core.constant.PaymentOperationType;
import com.webculcate.event.reservation.service.core.model.entity.embedded.TimeLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.PAYMENT_SEQUENCE_NAME;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.PAYMENT_TABLE_NAME;

@Data
@Builder
@Entity
@Table(name = PAYMENT_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id
    @SequenceGenerator(
            name = PAYMENT_SEQUENCE_NAME,
            sequenceName = PAYMENT_SEQUENCE_NAME,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = PAYMENT_SEQUENCE_NAME
    )
    private Long paymentId;

    @Column(nullable = false)
    private Long purchasedBy;

    private Double amount;

    private PaymentOperationType paymentOperation;

    @Embedded
    private TimeLog timeLog;

    @Version
    private Long version;

    public Payment(Long paymentId, Long version) {
        this.paymentId = paymentId;
        this.version = version;
    }

}
