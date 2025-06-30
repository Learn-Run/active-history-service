package com.unionclass.activehistoryservice.domain.activehistory.application;

import com.unionclass.activehistoryservice.common.kafka.entity.event.CommentCreatedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.CommentDeletedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.PostCreatedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.ReviewCreatedEvent;
import com.unionclass.activehistoryservice.common.response.CustomPageImpl;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryCountReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryCountResDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;

public interface ActiveHistoryService {

    void createReviewActiveHistory(ReviewCreatedEvent reviewCreatedEvent);

    void createPostActiveHistory(PostCreatedEvent postCreatedEvent);

    void createCommentActiveHistory(CommentCreatedEvent commentCreatedEvent);

    CustomPageImpl<GetActiveHistoryResDto> getActiveHistory(GetActiveHistoryReqDto getActiveHistoryReqDto);

    GetActiveHistoryCountResDto getActiveHistoryCount(GetActiveHistoryCountReqDto getActiveHistoryCountReqDto);

    void deleteCommentActiveHistory(CommentDeletedEvent event);
}
