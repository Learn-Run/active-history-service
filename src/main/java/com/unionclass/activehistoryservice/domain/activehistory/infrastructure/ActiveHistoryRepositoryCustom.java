package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface ActiveHistoryRepositoryCustom {

    PageImpl<GetActiveHistoryResDto> findByOffset(String memberUuid, ActiveHistoryType type, Pageable pageable);
}
