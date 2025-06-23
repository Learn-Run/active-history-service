package com.unionclass.activehistoryservice.common.kafka.entity.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewCreatedEvent {

    private String reviewerUuid;
    private String revieweeUuid;
    private String reviewId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ReviewCreatedEvent(
            String reviewerUuid, String revieweeUuid, String reviewId,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.reviewerUuid = reviewerUuid;
        this.revieweeUuid = revieweeUuid;
        this.reviewId = reviewId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
