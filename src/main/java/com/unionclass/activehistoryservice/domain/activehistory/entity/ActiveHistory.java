package com.unionclass.activehistoryservice.domain.activehistory.entity;

import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "active_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@CompoundIndex(
        name = "idx_member_uuid_type_createdAt",
        def = "{'memberUuid': 1, 'type': 1, 'createdAt': -1}"
)
public class ActiveHistory {

    @Id
    private String id;
    private String memberUuid;
    private ActiveHistoryType type;
    private String uuid;

    private Boolean deleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ActiveHistory(
            String id, String memberUuid, ActiveHistoryType type, String uuid,
            Boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.type = type;
        this.uuid = uuid;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void delete(Boolean deleted) {
        this.deleted = deleted;
    }

    public void delete() {
        this.deleted = true;
    }
}
