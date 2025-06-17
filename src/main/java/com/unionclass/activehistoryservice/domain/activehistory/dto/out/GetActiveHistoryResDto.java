package com.unionclass.activehistoryservice.domain.activehistory.dto.out;

import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import com.unionclass.activehistoryservice.domain.activehistory.vo.out.GetActiveHistoryResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetActiveHistoryResDto {

    private ActiveHistoryType type;
    private String uuid;

    @Builder
    public GetActiveHistoryResDto(ActiveHistoryType type, String uuid) {
        this.type = type;
        this.uuid = uuid;
    }

    public static GetActiveHistoryResDto from(ActiveHistory activeHistory) {
        return GetActiveHistoryResDto.builder()
                .type(activeHistory.getType())
                .uuid(activeHistory.getUuid())
                .build();
    }

    public static GetActiveHistoryResVo toVo(GetActiveHistoryResDto getActiveHistoryResDto) {
        return GetActiveHistoryResVo.builder()
                .type(getActiveHistoryResDto.type)
                .uuid(getActiveHistoryResDto.uuid)
                .build();
    }
}
