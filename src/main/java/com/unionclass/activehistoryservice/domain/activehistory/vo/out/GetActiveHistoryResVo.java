package com.unionclass.activehistoryservice.domain.activehistory.vo.out;

import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Getter;

@Getter
public class GetActiveHistoryResVo {

    private ActiveHistoryType type;
    private String uuid;
}
