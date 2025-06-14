package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ActiveHistoryRepositoryCustomImpl implements ActiveHistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<GetActiveHistoryResDto> findByCursor(String memberUuid, String cursorId, int size) {
        return null;
    }

    @Override
    public Page<GetActiveHistoryResDto> findByCursorAndType(String memberUuid, ActiveHistoryType type, String cursorId, int size) {
        return null;
    }

    @Override
    public Page<GetActiveHistoryResDto> findByMemberUuid(String memberUuid, Pageable pageable) {
        return null;
    }

    @Override
    public Page<GetActiveHistoryResDto> findByMemberUuidAndType(String memberUuid, ActiveHistoryType type, Pageable pageable) {
        return null;
    }
}
