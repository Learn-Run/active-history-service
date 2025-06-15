package com.unionclass.activehistoryservice.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SUCCESS_GET_ACTIVE_HISTORY_INFORMATION("활동이력 정보 조회에 성공하였습니다.");

    private final String message;
}
