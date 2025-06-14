package com.unionclass.activehistoryservice.domain.activehistory.dto.in;

import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
public class GetActiveHistoryReqDto {

    private String memberUuid;
    private ActiveHistoryType type;
    private String cursorId;
    private int page;
    private int size;

    @Builder
    public GetActiveHistoryReqDto(String memberUuid, ActiveHistoryType type, String cursorId, int page, int size) {
        this.memberUuid = memberUuid;
        this.type = type;
        this.cursorId = cursorId;
        this.page = page;
        this.size = size;
    }

    public static GetActiveHistoryReqDto of(
            String memberUuid, ActiveHistoryType type, String cursorId, int page, int size
    ) {
        return GetActiveHistoryReqDto.builder()
                .memberUuid(memberUuid)
                .type(type)
                .cursorId(cursorId)
                .page(page)
                .size(size)
                .build();
    }

    public Pageable toPageable() {
        return PageRequest.of(page - 1, size);
    }
}
