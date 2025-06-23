package com.unionclass.activehistoryservice.domain.activehistory.dto.in;

import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetActiveHistoryReqDto {

    private String memberUuid;
    private int page;
    private int size;
    private ActiveHistoryType type;

    @Builder
    public GetActiveHistoryReqDto(String memberUuid, int page, int size, ActiveHistoryType type) {
        this.memberUuid = memberUuid;
        this.page = page;
        this.size = size;
        this.type = type;
    }

    public static GetActiveHistoryReqDto of(
            String memberUuid, int page, int size, ActiveHistoryType type) {
        return GetActiveHistoryReqDto.builder()
                .memberUuid(memberUuid)
                .page(page)
                .size(size)
                .type(type)
                .build();
    }
}
