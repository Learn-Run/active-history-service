package com.unionclass.activehistoryservice.domain.activehistory.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.unionclass.activehistoryservice.common.exception.BaseException;
import com.unionclass.activehistoryservice.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActiveHistoryType {

    REVIEW("리뷰"),
    POST("질문"),
    COMMENT("댓글")
    ;

    private final String type;

    @JsonValue
    public String getActiveHistoryType() { return type; }

    @JsonCreator
    public static ActiveHistoryType fromString(String value) {
        for (ActiveHistoryType activeHistoryType : ActiveHistoryType.values()) {
            if (activeHistoryType.type.equals(value)) {
                return activeHistoryType;
            }
        }
        throw new BaseException(ErrorCode.INVALID_ACTIVE_HISTORY_TYPE_VALUE);
    }
}
