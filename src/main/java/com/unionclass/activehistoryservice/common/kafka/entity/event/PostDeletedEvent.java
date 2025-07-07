package com.unionclass.activehistoryservice.common.kafka.entity.event;

import lombok.Getter;

@Getter
public class PostDeletedEvent {

    private String postUuid;
}
