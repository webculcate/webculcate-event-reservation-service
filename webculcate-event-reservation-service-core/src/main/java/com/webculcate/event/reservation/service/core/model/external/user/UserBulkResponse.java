package com.webculcate.event.reservation.service.core.model.external.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBulkResponse {

    private List<UserDto> userList;

}

