package com.unionclass.activehistoryservice.domain.activehistory.application;

import com.unionclass.activehistoryservice.common.exception.BaseException;
import com.unionclass.activehistoryservice.common.exception.ErrorCode;
import com.unionclass.activehistoryservice.common.kafka.entity.event.PostCreatedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.ReviewCreatedEvent;
import com.unionclass.activehistoryservice.common.response.CursorPage;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.CreatePostActiveHistoryDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.CreateReviewActiveHistoryDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryCountReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryCountResDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import com.unionclass.activehistoryservice.domain.activehistory.infrastructure.ActiveHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
            log.warn("리뷰 활동이력 저장 실패 - memberUuid: {}, uuid: {}, message: {}",
                    reviewCreatedEvent.getMemberUuid(), reviewCreatedEvent.getReviewId(), e.getMessage(), e);
            throw new BaseException(ErrorCode.FAILED_TO_SAVE_REVIEW_ACTIVE_HISTORY);
        }
    }

    @Override
    public void createPostActiveHistory(PostCreatedEvent postCreatedEvent) {
        try {
            activeHistoryRepository.save(CreatePostActiveHistoryDto.from(postCreatedEvent).toEntity());
            log.info("질문 활동이력 저장 성공 - memberUuid: {}, uuid: {}",
                    postCreatedEvent.getMemberUuid(), postCreatedEvent.getPostUuid());
        } catch (Exception e) {
            log.warn("질문 활동이력 저장 실패 - memberUuid: {}, uuid: {}, message: {}",
                    postCreatedEvent.getMemberUuid(), postCreatedEvent.getPostUuid(), e.getMessage(), e);
            throw new BaseException(ErrorCode.FAILED_TO_SAVE_POST_ACTIVE_HISTORY);
        }

    }

    @Override
    public CursorPage<GetActiveHistoryResDto> getActiveHistory(GetActiveHistoryReqDto getActiveHistoryReqDto) {
        try {
            String cursor = getActiveHistoryReqDto.getCursorId() != null
                    ? getActiveHistoryReqDto.getCursorId()
                    : activeHistoryRepository.findCursorByOffset(
                    getActiveHistoryReqDto.getMemberUuid(),
                    getActiveHistoryReqDto.getType(),
                    getActiveHistoryReqDto.getPage()  * getActiveHistoryReqDto.getSize());

            return activeHistoryRepository.findByCursor(
                    getActiveHistoryReqDto.getMemberUuid(),
                    getActiveHistoryReqDto.getType(),
                    cursor,
                    getActiveHistoryReqDto.getPage(),
                    getActiveHistoryReqDto.getSize());
        } catch (Exception e) {
            log.warn("활동 이력 조회 실패 - memberUuid: {}, message: {}",
                    getActiveHistoryReqDto.getMemberUuid(), e.getMessage(), e);
            throw new BaseException(ErrorCode.FAILED_TO_LOAD_ACTIVE_HISTORY_INFORMATION);
        }
    }

    @Override
    public GetActiveHistoryCountResDto getActiveHistoryCount(GetActiveHistoryCountReqDto getActiveHistoryCountReqDto) {

        try {
            switch (getActiveHistoryCountReqDto.getPeriod()) {
                case TODAY -> {
                    LocalDate today = LocalDate.now();
                    LocalDateTime startOfDay = today.atStartOfDay();
                    LocalDateTime endOfDay = today.plusDays(1).atStartOfDay().minusNanos(1);

                    Long postCount = activeHistoryRepository.countByMemberUuidAndTypeAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.POST, startOfDay, endOfDay);
                    Long commentCount = activeHistoryRepository.countByMemberUuidAndTypeAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.COMMENT, startOfDay, endOfDay);
                    Long reviewCount = activeHistoryRepository.countByMemberUuidAndTypeAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.REVIEW, startOfDay, endOfDay);
                    Long totalCount = activeHistoryRepository.countByMemberUuidAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), startOfDay, endOfDay);

                    return GetActiveHistoryCountResDto.of(postCount, commentCount, reviewCount, totalCount);
                }
                case TOTAL -> {
                    Long postCount = activeHistoryRepository.countByMemberUuidAndType(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.POST);
                    Long commentCount = activeHistoryRepository.countByMemberUuidAndType(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.COMMENT);
                    Long reviewCount = activeHistoryRepository.countByMemberUuidAndType(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.REVIEW);
                    Long totalCount = activeHistoryRepository.countByMemberUuid(getActiveHistoryCountReqDto.getMemberUuid());

                    return GetActiveHistoryCountResDto.of(postCount, commentCount, reviewCount, totalCount);
                }
                default -> throw new BaseException(ErrorCode.INVALID_PERIOD_VALUE);
            }
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            log.warn("활동 이력 개수 조회 실패 - memberUuid: {}, message: {}",
                    getActiveHistoryCountReqDto.getMemberUuid(), e.getMessage(), e);
            throw new BaseException(ErrorCode.FAILED_TO_GET_ACTIVE_HISTORY_COUNT);
        }
    }
}