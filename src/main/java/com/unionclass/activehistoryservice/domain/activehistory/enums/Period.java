package com.unionclass.activehistoryservice.domain.activehistory.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.unionclass.activehistoryservice.common.exception.BaseException;
import com.unionclass.activehistoryservice.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Period {

    TODAY("today"),
    TOTAL("total"),
    ;

    private final String period;

    @JsonValue
    public String getValue() { return period; }

    @JsonCreator
    public static Period fromString(String value) {
        for (Period period : Period.values()) {
            if (period.period.equals(value)) {
                return period;
            }
        }
        throw new BaseException(ErrorCode.INVALID_PERIOD_VALUE);
    }
}
