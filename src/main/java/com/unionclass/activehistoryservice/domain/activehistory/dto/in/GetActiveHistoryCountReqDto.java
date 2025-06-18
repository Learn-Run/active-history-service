package com.unionclass.activehistoryservice.domain.activehistory.dto.in;

import com.unionclass.activehistoryservice.domain.activehistory.enums.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetActiveHistoryCountReqDto {

    private String memberUuid;
    private Period period;

    @Builder
    public GetActiveHistoryCountReqDto(String memberUuid, Period period) {
        this.memberUuid = memberUuid;
        this.period = period;
    }

    public static GetActiveHistoryCountReqDto of(String memberUuid, Period period) {
        return GetActiveHistoryCountReqDto.builder()
                .memberUuid(memberUuid)
                .period(period)
                .build();
    }
}
