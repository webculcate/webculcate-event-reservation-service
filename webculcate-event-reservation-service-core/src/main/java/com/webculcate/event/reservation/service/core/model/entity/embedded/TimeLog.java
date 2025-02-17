package com.webculcate.event.reservation.service.core.model.entity.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TimeLog {

    @CreatedDate
    @Column(nullable = false)
    private Long creationTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Long modificationTime;

}
