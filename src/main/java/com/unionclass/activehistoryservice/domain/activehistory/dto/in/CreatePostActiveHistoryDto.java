package com.unionclass.activehistoryservice.domain.activehistory.dto.in;

import com.unionclass.activehistoryservice.common.kafka.entity.event.PostCreatedEvent;
import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreatePostActiveHistoryDto {

    private String memberUuid;
    private ActiveHistoryType type;
    private String uuid;
    private Boolean deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public CreatePostActiveHistoryDto(
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

    public static CreatePostActiveHistoryDto from(PostCreatedEvent postCreatedEvent) {
        return CreatePostActiveHistoryDto.builder()
                .memberUuid(postCreatedEvent.getMemberUuid())
                .type(ActiveHistoryType.POST)
                .uuid(postCreatedEvent.getPostUuid())
                .deleted(postCreatedEvent.isDeletedStatus())
                .createdAt(postCreatedEvent.getCreatedAt())
                .updatedAt(postCreatedEvent.getUpdatedAt())
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
