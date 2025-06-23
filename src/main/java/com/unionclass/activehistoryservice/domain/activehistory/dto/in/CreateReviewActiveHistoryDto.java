package com.unionclass.activehistoryservice.domain.activehistory.dto.in;

import com.unionclass.activehistoryservice.common.kafka.entity.event.ReviewCreatedEvent;
import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateReviewActiveHistoryDto {

    private String memberUuid;
    private ActiveHistoryType type;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public CreateReviewActiveHistoryDto(
            String memberUuid, ActiveHistoryType type, String uuid,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.memberUuid = memberUuid;
        this.type = type;
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CreateReviewActiveHistoryDto forWriter(ReviewCreatedEvent reviewCreatedEvent) {
        return CreateReviewActiveHistoryDto.builder()
                .memberUuid(reviewCreatedEvent.getReviewerUuid())
                .type(ActiveHistoryType.REVIEW_WRITE)
                .uuid(reviewCreatedEvent.getReviewId())
                .createdAt(reviewCreatedEvent.getCreatedAt())
                .updatedAt(reviewCreatedEvent.getUpdatedAt())
                .build();
    }

    public static CreateReviewActiveHistoryDto forReceiver(ReviewCreatedEvent reviewCreatedEvent) {
        return CreateReviewActiveHistoryDto.builder()
                .memberUuid(reviewCreatedEvent.getRevieweeUuid())
                .type(ActiveHistoryType.REVIEW_RECEIVED)
                .uuid(reviewCreatedEvent.getReviewId())
                .createdAt(reviewCreatedEvent.getCreatedAt())
                .updatedAt(reviewCreatedEvent.getUpdatedAt())
                .build();
    }

    public ActiveHistory toEntity() {
        return ActiveHistory.builder()
                .memberUuid(memberUuid)
                .type(type)
                .uuid(uuid)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
