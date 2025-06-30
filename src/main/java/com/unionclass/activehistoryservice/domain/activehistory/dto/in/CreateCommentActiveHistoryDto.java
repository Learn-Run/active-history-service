package com.unionclass.activehistoryservice.domain.activehistory.dto.in;

import com.unionclass.activehistoryservice.common.kafka.entity.event.CommentCreatedEvent;
import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreateCommentActiveHistoryDto {

    private String memberUuid;
    private ActiveHistoryType type;
    private String uuid;
    private Boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public CreateCommentActiveHistoryDto(
            String memberUuid, ActiveHistoryType type, String uuid,
            Boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.memberUuid = memberUuid;
        this.type = type;
        this.uuid = uuid;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CreateCommentActiveHistoryDto from(CommentCreatedEvent commentCreatedEvent) {
        return CreateCommentActiveHistoryDto.builder()
                .memberUuid(commentCreatedEvent.getMemberUuid())
                .type(ActiveHistoryType.COMMENT)
                .uuid(commentCreatedEvent.getCommentUuid())
                .deleted(commentCreatedEvent.getDeleted())
                .createdAt(commentCreatedEvent.getCreatedAt())
                .updatedAt(commentCreatedEvent.getUpdatedAt())
                .build();
    }

    public ActiveHistory toEntity() {
        return ActiveHistory.builder()
                .memberUuid(memberUuid)
                .type(type)
                .uuid(uuid)
                .deleted(deleted)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
