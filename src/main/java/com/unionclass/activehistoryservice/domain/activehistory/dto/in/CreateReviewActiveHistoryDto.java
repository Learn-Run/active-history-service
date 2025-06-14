package com.unionclass.activehistoryservice.domain.activehistory.dto.in;

import com.unionclass.activehistoryservice.common.kafka.entity.ReviewCreatedEvent;
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

    public static CreateReviewActiveHistoryDto from(ReviewCreatedEvent reviewCreatedEvent) {
        return CreateReviewActiveHistoryDto.builder()
                .memberUuid(reviewCreatedEvent.getMemberUuid())
                .type(ActiveHistoryType.TYPE_REVIEW)
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
