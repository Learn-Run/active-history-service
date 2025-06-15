package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActiveHistoryRepository extends MongoRepository<ActiveHistory, String>, ActiveHistoryRepositoryCustom {
}
