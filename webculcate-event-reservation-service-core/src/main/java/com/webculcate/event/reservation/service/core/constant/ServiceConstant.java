package com.webculcate.event.reservation.service.core.constant;

public class ServiceConstant {

    // database

    public static final String SCHEDULED_EVENT_RESERVATION_TABLE_NAME = "service_scheduled_event_reservation";

    public static final String SCHEDULED_EVENT_RESERVATION_SEQUENCE_NAME = SCHEDULED_EVENT_RESERVATION_TABLE_NAME + "_sequence";

    public static final String PAYMENT_TABLE_NAME = "service_payment";

    public static final String PAYMENT_SEQUENCE_NAME = PAYMENT_TABLE_NAME + "_sequence";

    public static final String PAYMENT_FOREIGN_KEY = "fk_service_payment";

    // general

    public static final Integer ZERO_INTEGER = 0;

    public static final String PROXY_ENABLED = "${application.proxy.enabled}";

    public static final String STRING_UNDERSCORE = "_";

    public static final String STRING_SPACE = " ";


    // external

    public static final String WEBCULCATE_USER_SERVICE = "WEBCULCATE-USER-SERVICE-CORE";

    public static final String WEBCULCATE_EVENT_SERVICE = "WEBCULCATE-EVENT-SERVICE-CORE";

    // topic

    public static final String WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK = "webculcateCapacityReductionRollback";

}
