package com.unionclass.activehistoryservice.common.kafka.util;

import com.unionclass.activehistoryservice.common.kafka.entity.event.*;
import com.unionclass.activehistoryservice.domain.activehistory.application.ActiveHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ActiveHistoryService activeHistoryService;

    @KafkaListener(
            topics = "comment-created",
            groupId = "active-history-group",
            containerFactory = "commentCreatedEventListener"
    )
    public void consumeCommentCreatedEvent(
            CommentCreatedEvent commentCreatedEvent,
            ConsumerRecord<String, CommentCreatedEvent> consumerRecord
    ) {
        log.info("댓글 생성 이벤트 수신 완료: {}", commentCreatedEvent);
        log.info("댓글 생성 이벤트 수신 - topic: {}, partition: {}, offset: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        activeHistoryService.createCommentActiveHistory(commentCreatedEvent);
    }

    @KafkaListener(
            topics = "review-created",
            groupId = "active-history-group",
            containerFactory = "reviewCreatedEventListener"
    )
    public void consumeReviewCreatedEvent(
            ReviewCreatedEvent reviewCreatedEvent,
            ConsumerRecord<String, ReviewCreatedEvent> consumerRecord
    ) {
        log.info("리뷰 생성 이벤트 수신 완료: {}", reviewCreatedEvent);
        log.info("리뷰 생성 이벤트 수신 - topic: {}, partition: {}, offset: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        activeHistoryService.createReviewActiveHistory(reviewCreatedEvent);
    }

    @KafkaListener(
            topics = "post-create-send-read",
            groupId = "active-history-group",
            containerFactory = "postCreatedEventListener"
    )
    public void consumePostCreatedEvent(
            PostCreatedEvent postCreatedEvent,
            ConsumerRecord<String, PostCreatedEvent> consumerRecord
    ) {
        log.info("질문 생성 이벤트 수신 완료: {}", postCreatedEvent);
        log.info("질문 생성 이벤트 수신 - topic: {}, partition: {}, offset: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        activeHistoryService.createPostActiveHistory(postCreatedEvent);
    }

    @KafkaListener(
            topics = "post-delete-send-read",
            groupId = "active-history-group",
            containerFactory = "postDeletedEventListener"
    )
    public void consumePostDeletedEvent(
            PostDeletedEvent postDeletedEvent,
            ConsumerRecord<String, PostDeletedEvent> consumerRecord
    ) {
        log.info("질문 삭제 이벤트 수신 완료: {}", postDeletedEvent);
        log.info("질문 삭제 이벤트 수신 - topic: {}, partition: {}, offset: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        activeHistoryService.deletedPostActiveHistory(postDeletedEvent);
    }

    @KafkaListener(
            topics = "comment-deleted",
            groupId = "active-history-group",
            containerFactory = "commentDeletedEventListener"
    )
    public void consumeCommentDeletedEvent(
            CommentDeletedEvent commentDeletedEvent,
            ConsumerRecord<String, CommentDeletedEvent> consumerRecord
    ) {
        log.info("댓글 삭제 이벤트 수신 완료: {}", commentDeletedEvent);
        log.info("댓글 삭제 이벤트 수신 - topic: {}, partition: {}, offset: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        activeHistoryService.deleteCommentActiveHistory(commentDeletedEvent);
    }
}
