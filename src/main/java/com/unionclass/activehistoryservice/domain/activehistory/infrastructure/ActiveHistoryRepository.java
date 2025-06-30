package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ActiveHistoryRepository extends MongoRepository<ActiveHistory, String>, ActiveHistoryRepositoryCustom {

    Optional<ActiveHistory> findByMemberUuidAndUuid(String memberUuid, String commentUuid);

    Long countByMemberUuidAndTypeAndDeletedFalseAndCreatedAtBetween(String memberUuid, ActiveHistoryType activeHistoryType, LocalDateTime startOfDay, LocalDateTime endOfDay);

    Long countByMemberUuidAndDeletedFalseAndCreatedAtBetween(String memberUuid, LocalDateTime startOfDay, LocalDateTime endOfDay);

    Long countByMemberUuidAndTypeAndDeletedFalse(String memberUuid, ActiveHistoryType activeHistoryType);

    Long countByMemberUuidAndDeletedFalse(String memberUuid);
}
