package com.unionclass.activehistoryservice.domain.activehistory.application;

import com.unionclass.activehistoryservice.common.exception.BaseException;
import com.unionclass.activehistoryservice.common.exception.ErrorCode;
import com.unionclass.activehistoryservice.common.kafka.entity.ReviewCreatedEvent;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.CreateReviewActiveHistoryDto;
import com.unionclass.activehistoryservice.domain.activehistory.infrastructure.ActiveHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActiveHistoryServiceImpl implements ActiveHistoryService {

    private final ActiveHistoryRepository activeHistoryRepository;

    @Transactional
    @Override
    public void createReviewActiveHistory(ReviewCreatedEvent reviewCreatedEvent) {
        try {
            activeHistoryRepository.save(CreateReviewActiveHistoryDto.from(reviewCreatedEvent).toEntity());
            log.info("리뷰 활동이력 저장 성공 - memberUuid: {}, uuid: {}",
                    reviewCreatedEvent.getMemberUuid(), reviewCreatedEvent.getReviewId());
        } catch (Exception e) {
            log.warn("리뷰 활동이력 저장 실패 - memberUuid: {}, uuid: {}",
                    reviewCreatedEvent.getMemberUuid(), reviewCreatedEvent.getReviewId());
            throw new BaseException(ErrorCode.FAILED_TO_SAVE_REVIEW_ACTIVE_HISTORY);
        }
    }
}
