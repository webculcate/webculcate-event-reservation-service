package com.webculcate.event.reservation.service.core.constant;

public class ServiceConstant {

    // database

    public static final String SCHEDULED_EVENT_RESERVATION_TABLE_NAME = "service_scheduled_event_reservation";

    public static final String SCHEDULED_EVENT_RESERVATION_SEQUENCE_NAME = SCHEDULED_EVENT_RESERVATION_TABLE_NAME + "_sequence";

    public static final String SCHEDULED_EVENT_ID_COLUMN_NAME = "scheduled_event_id";

    public static final String SCHEDULED_EVENT_ID_INDEX_NAME = SCHEDULED_EVENT_ID_COLUMN_NAME + "_index";

    public static final String PAYMENT_TABLE_NAME = "service_payment";

    public static final String PAYMENT_SEQUENCE_NAME = PAYMENT_TABLE_NAME + "_sequence";

    public static final String PAYMENT_FOREIGN_KEY = "fk_service_payment";

    // general

    public static final Integer ZERO_INTEGER = 0;

    public static final Long ZERO_LONG = 0L;

    public static final String PROXY_ENABLED = "${application.proxy.enabled}";

    public static final String STRING_UNDERSCORE = "_";

    public static final String STRING_SPACE = " ";

    public static final String NOT_AVAILABLE = "NOT_AVAILABLE";


    // external

    public static final String WEBCULCATE_USER_SERVICE = "WEBCULCATE-USER-SERVICE-CORE";

    public static final String WEBCULCATE_EVENT_SERVICE = "WEBCULCATE-EVENT-SERVICE-CORE";

    // topic

    public static final String WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK = "webculcateCapacityReductionRollback";

    // bean

    public static final String DEFAULT_TASK_EXECUTOR = "DEFAULT_TASK_EXECUTOR";

    public static final String DEFAULT_TASK_EXECUTOR_PREFIX = DEFAULT_TASK_EXECUTOR + "--";

}
