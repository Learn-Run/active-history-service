package com.unionclass.activehistoryservice.common.kafka.util;

import com.unionclass.activehistoryservice.common.kafka.entity.event.ReviewCreatedEvent;
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
            topics = "review-created",
            groupId = "active-history-group",
            containerFactory = "reviewCreatedEventListener"
    )
    public void consumeReviewEvent(
            ReviewCreatedEvent reviewCreatedEvent,
            ConsumerRecord<String, ReviewCreatedEvent> consumerRecord
    ) {
        log.info("리뷰 생성 이벤트 수신 완료: {}", reviewCreatedEvent);
        log.info("리뷰 생성 이벤트 수신 - topic: {}, partition: {}, offset: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        activeHistoryService.createReviewActiveHistory(reviewCreatedEvent);
    }
}
