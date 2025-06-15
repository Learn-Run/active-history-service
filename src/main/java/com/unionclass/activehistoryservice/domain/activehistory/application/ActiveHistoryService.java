package com.unionclass.activehistoryservice.domain.activehistory.application;

import com.unionclass.activehistoryservice.common.kafka.entity.ReviewCreatedEvent;
import com.unionclass.activehistoryservice.common.response.CursorPage;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;

public interface ActiveHistoryService {

    void createReviewActiveHistory(ReviewCreatedEvent reviewCreatedEvent);
    CursorPage<GetActiveHistoryResDto> getActiveHistory(GetActiveHistoryReqDto getActiveHistoryReqDto);
}
