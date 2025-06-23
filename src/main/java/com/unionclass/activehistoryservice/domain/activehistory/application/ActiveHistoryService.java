package com.unionclass.activehistoryservice.domain.activehistory.application;

import com.unionclass.activehistoryservice.common.kafka.entity.event.PostCreatedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.ReviewCreatedEvent;
import com.unionclass.activehistoryservice.common.response.CursorPage;
import com.unionclass.activehistoryservice.common.response.CustomPageImpl;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryCountReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryCountResDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import org.springframework.data.domain.Page;

public interface ActiveHistoryService {

    void createReviewActiveHistory(ReviewCreatedEvent reviewCreatedEvent);
    void createPostActiveHistory(PostCreatedEvent postCreatedEvent);
    CustomPageImpl<GetActiveHistoryResDto> getActiveHistory(GetActiveHistoryReqDto getActiveHistoryReqDto);
    GetActiveHistoryCountResDto getActiveHistoryCount(GetActiveHistoryCountReqDto getActiveHistoryCountReqDto);
}
