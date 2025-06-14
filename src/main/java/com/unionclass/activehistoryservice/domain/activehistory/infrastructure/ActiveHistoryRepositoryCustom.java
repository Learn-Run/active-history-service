package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActiveHistoryRepositoryCustom {

    Page<GetActiveHistoryResDto> findByCursor(String memberUuid, String cursorId, int size);
    Page<GetActiveHistoryResDto> findByCursorAndType(String memberUuid, ActiveHistoryType type, String cursorId, int size);
    Page<GetActiveHistoryResDto> findByMemberUuid(String memberUuid, Pageable pageable);
    Page<GetActiveHistoryResDto> findByMemberUuidAndType(String memberUuid, ActiveHistoryType type, Pageable pageable);
}
