package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import org.springframework.data.domain.Page;

public interface ActiveHistoryRepositoryCustom {

    Page<GetActiveHistoryResDto> findByOffset(String memberUuid, ActiveHistoryType type, int page, int size);
}
