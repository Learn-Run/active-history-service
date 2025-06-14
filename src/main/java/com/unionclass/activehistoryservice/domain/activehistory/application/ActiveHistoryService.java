package com.unionclass.activehistoryservice.domain.activehistory.application;

import com.unionclass.activehistoryservice.common.kafka.entity.ReviewCreatedEvent;

public interface ActiveHistoryService {

    void createReviewActiveHistory(ReviewCreatedEvent reviewCreatedEvent);
}
