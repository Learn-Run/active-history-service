package com.unionclass.activehistoryservice.domain.activehistory.application;

import com.unionclass.activehistoryservice.common.exception.BaseException;
import com.unionclass.activehistoryservice.common.exception.ErrorCode;
import com.unionclass.activehistoryservice.common.kafka.entity.event.*;
import com.unionclass.activehistoryservice.common.response.CustomPageImpl;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.*;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryCountResDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import com.unionclass.activehistoryservice.domain.activehistory.infrastructure.ActiveHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
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

            activeHistoryRepository.save(CreateReviewActiveHistoryDto.forWriter(reviewCreatedEvent).toEntity());
            activeHistoryRepository.save(CreateReviewActiveHistoryDto.forReceiver(reviewCreatedEvent).toEntity());

            log.info("리뷰 활동이력 저장 성공 - 작성자: {}, 대상자: {}, uuid: {}",
                    reviewCreatedEvent.getReviewerUuid(), reviewCreatedEvent.getRevieweeUuid(), reviewCreatedEvent.getReviewId());

        } catch (Exception e) {
            log.warn("리뷰 활동이력 저장 실패 - 작성자: {}, 대상자: {}, uuid: {}, message: {}",
                    reviewCreatedEvent.getReviewerUuid(), reviewCreatedEvent.getRevieweeUuid(),
                    reviewCreatedEvent.getReviewId(), e.getMessage(), e);
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
    public void createCommentActiveHistory(CommentCreatedEvent commentCreatedEvent) {

        try {

            activeHistoryRepository.save(CreateCommentActiveHistoryDto.from(commentCreatedEvent).toEntity());

            log.info("댓글 활동이력 저장 성공 - memberUuid: {}, uuid: {}",
                    commentCreatedEvent.getMemberUuid(), commentCreatedEvent.getCommentUuid());

        } catch (Exception e) {

            log.warn("댓글 활동이력 저장 실패 - memberUuid: {}, uuid: {}, message: {}",
                    commentCreatedEvent.getMemberUuid(), commentCreatedEvent.getCommentUuid(), e.getMessage(), e);

            throw new BaseException(ErrorCode.FAILED_TO_SAVE_COMMENT_ACTIVE_HISTORY);

        }

    }

    @Override
    public CustomPageImpl<GetActiveHistoryResDto> getActiveHistory(GetActiveHistoryReqDto getActiveHistoryReqDto) {
        try {

            return CustomPageImpl.from(
                    activeHistoryRepository.findByOffset(
                    getActiveHistoryReqDto.getMemberUuid(),
                    getActiveHistoryReqDto.getType(),
                    PageRequest.of(getActiveHistoryReqDto.getPage(), getActiveHistoryReqDto.getSize())));

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

                    Long postCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalseAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.POST, startOfDay, endOfDay);
                    Long commentCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalseAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.COMMENT, startOfDay, endOfDay);
                    Long reviewWriteCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalseAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.REVIEW_WRITE, startOfDay, endOfDay);
                    Long reviewReceivedCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalseAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.REVIEW_RECEIVED, startOfDay, endOfDay);
                    Long totalCount = activeHistoryRepository.countByMemberUuidAndDeletedFalseAndCreatedAtBetween(
                            getActiveHistoryCountReqDto.getMemberUuid(), startOfDay, endOfDay);

                    return GetActiveHistoryCountResDto.of(postCount, commentCount, reviewWriteCount, reviewReceivedCount, totalCount);
                }
                case TOTAL -> {
                    Long postCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalse(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.POST);
                    Long commentCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalse(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.COMMENT);
                    Long reviewWriteCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalse(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.REVIEW_WRITE);
                    Long reviewReceivedCount = activeHistoryRepository.countByMemberUuidAndTypeAndDeletedFalse(
                            getActiveHistoryCountReqDto.getMemberUuid(), ActiveHistoryType.REVIEW_RECEIVED);
                    Long totalCount = activeHistoryRepository.countByMemberUuidAndDeletedFalse(getActiveHistoryCountReqDto.getMemberUuid());

                    return GetActiveHistoryCountResDto.of(postCount, commentCount, reviewWriteCount, reviewReceivedCount, totalCount);
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

    @Transactional
    @Override
    public void deleteCommentActiveHistory(CommentDeletedEvent event) {

        try {

            ActiveHistory activeHistory = activeHistoryRepository
                    .findByMemberUuidAndUuid(event.getMemberUuid(), event.getCommentUuid())
                    .orElseThrow(
                            () -> new BaseException(ErrorCode.FAILED_TO_FIND_ACTIVE_HISTORY_BY_MEMBER_UUID_AND_COMMENT_UUID)
                    );

            activeHistory.delete(event.getDeleted());

            activeHistoryRepository.save(activeHistory);

            log.info("활동 이력 삭제 상태 변경 성공 - memberUuid: {}, commentUuid: {}",
                    event.getMemberUuid(), event.getCommentUuid());

        } catch (Exception e) {

            log.warn("활동 이력 삭제 상태 변경 실패 - memberUuid: {}, commentUuid: {}, message: {}",
                    event.getMemberUuid(), event.getCommentUuid(), e.getMessage(), e);

            throw new BaseException(ErrorCode.FAILED_TO_UPDATED_ACTIVE_HISTORY_DELETED_STATUS);
        }
    }

    @Transactional
    @Override
    public void deletedPostActiveHistory(PostDeletedEvent event) {

        try {

            ActiveHistory activeHistory = activeHistoryRepository.findByUuid(event.getPostUuid())
                    .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_FIND_ACTIVE_HISTORY_BY_POST_UUID));

            activeHistory.delete();

            activeHistoryRepository.save(activeHistory);

            log.info("활동 이력 삭제 상태 변경 성공 - memberUuid: {}, postUuid: {}",
                    activeHistory.getMemberUuid(), activeHistory.getUuid());

        } catch (Exception e) {

            log.warn("활동 이력 삭제 상태 변경 실패 - postUuid: {}, message: {}",
                    event.getPostUuid(), e.getMessage(), e);

            throw new BaseException(ErrorCode.FAILED_TO_UPDATED_ACTIVE_HISTORY_DELETED_STATUS);
        }
    }
}