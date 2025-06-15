package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.common.response.CursorPage;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;

public interface ActiveHistoryRepositoryCustom {

    CursorPage<GetActiveHistoryResDto> findByCursor(String memberUuid, ActiveHistoryType type, String cursorId, int page, int size);
    String findCursorByOffset(String memberUuid, ActiveHistoryType type, int offset);
}
