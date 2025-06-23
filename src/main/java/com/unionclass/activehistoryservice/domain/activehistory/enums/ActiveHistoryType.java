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

    REVIEW("REVIEW"),
    POST("POST"),
    COMMENT("COMMENT")
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
