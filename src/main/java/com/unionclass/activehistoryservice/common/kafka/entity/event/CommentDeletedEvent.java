package com.unionclass.activehistoryservice.common.kafka.entity.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeletedEvent {

    private String memberUuid;
    private String commentUuid;
    private Boolean deleted;

    @Builder
    public CommentDeletedEvent(
            String memberUuid, String commentUuid, Boolean deleted
    ) {
        this.memberUuid = memberUuid;
        this.commentUuid = commentUuid;
        this.deleted = deleted;
    }
}
