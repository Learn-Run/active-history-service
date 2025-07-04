package com.unionclass.activehistoryservice.common.kafka.entity.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostCreatedEvent {

    private String postUuid;
    private String memberUuid;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String title;
    private String contents;

    private boolean blindStatus;
    private boolean deletedStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostCreatedEvent(
            String postUuid,
            String memberUuid,
            Long mainCategoryId,
            Long subCategoryId,
            String title,
            String contents,
            boolean blindStatus,
            boolean deletedStatus,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
