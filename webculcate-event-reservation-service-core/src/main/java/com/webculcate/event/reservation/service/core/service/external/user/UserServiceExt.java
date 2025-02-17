package com.webculcate.event.reservation.service.core.service.external.user;

import com.webculcate.event.reservation.service.core.model.external.user.UserBulkRequest;
import com.webculcate.event.reservation.service.core.model.external.user.UserBulkResponse;
import com.webculcate.event.reservation.service.core.model.external.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.SERVICE_NULL;
import static com.webculcate.event.reservation.service.core.utility.ServiceHelper.nullHandledExtraction;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceExt {

    private final UserServiceClient userServiceClient;

    public List<UserDto> resolveUsers(Set<Long> userIds) {
        if(userIds.isEmpty())
            return Collections.EMPTY_LIST;
        List<UserDto> userDtoList = getUserDtoBulk(userIds);
        int difference = userIds.size() - userDtoList.size();
        if(difference > 0)
            log.info("unable to resolve " + difference + " users");
        return userDtoList;
    }

    public UserDto resolveUser(Long userId) {
        if(isNull(userId))
            return null;
        UserDto userDto = getUserDto(userId);
        if(isNull(userDto))
            log.info("unable to resolve user");
        return userDto;
    }

    private List<UserDto> getUserDtoBulk(Set<Long> userIds) {
        ResponseEntity<UserBulkResponse> response = userServiceClient.getUserBulk(new UserBulkRequest(userIds));
        Optional<List<UserDto>> optionalUserDtoList = nullHandledExtraction(() -> response.getBody().getUserList());
        return optionalUserDtoList.orElse(Collections.EMPTY_LIST);
    }

    private UserDto getUserDto(Long userId) {
        ResponseEntity<UserDto> response = userServiceClient.getUser(userId);
        Optional<UserDto> optionalUserDto = nullHandledExtraction(() -> response.getBody());
        return optionalUserDto.orElse(null);
    }

}
