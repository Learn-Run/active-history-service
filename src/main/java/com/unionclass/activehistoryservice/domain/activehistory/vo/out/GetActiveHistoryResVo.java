package com.unionclass.activehistoryservice.domain.activehistory.vo.out;

import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetActiveHistoryResVo {

    private ActiveHistoryType type;
    private String uuid;

    @Builder
    public GetActiveHistoryResVo(ActiveHistoryType type, String uuid) {
        this.type = type;
        this.uuid = uuid;
    }
}
