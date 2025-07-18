package com.unionclass.activehistoryservice.common.kafka.entity.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentCreatedEvent {

    private String memberUuid;
    private String commentUuid;
    private Boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public CommentCreatedEvent(
            String memberUuid, String commentUuid, Boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.memberUuid = memberUuid;
        this.commentUuid = commentUuid;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}