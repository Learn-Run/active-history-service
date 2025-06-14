package com.unionclass.activehistoryservice.domain.activehistory.dto.out;

import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Getter;

@Getter
public class GetActiveHistoryResDto {

    private ActiveHistoryType type;
    private String uuid;
}
