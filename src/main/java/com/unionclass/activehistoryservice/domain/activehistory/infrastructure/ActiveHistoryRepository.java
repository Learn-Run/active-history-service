package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface ActiveHistoryRepository extends MongoRepository<ActiveHistory, String>, ActiveHistoryRepositoryCustom {

    Long countByMemberUuidAndType(String memberUuid, ActiveHistoryType activeHistoryType);
    Long countByMemberUuid(String memberUuid);
    Long countByMemberUuidAndTypeAndCreatedAtBetween(String memberUuid, ActiveHistoryType type, LocalDateTime createdAtAfter, LocalDateTime createdAtBefore);
    Long countByMemberUuidAndCreatedAtBetween(String memberUuid, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
