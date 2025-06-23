package com.unionclass.activehistoryservice.domain.activehistory.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetActiveHistoryCountResVo {

    private Long postCount;
    private Long commentCount;
    private Long reviewWriteCount;
    private Long reviewReceivedCount;
    private Long totalCount;

    @Builder
    public GetActiveHistoryCountResVo(
            Long postCount, Long commentCount, Long reviewWriteCount, Long reviewReceivedCount, Long totalCount
    ) {
        this.postCount = postCount;
        this.commentCount = commentCount;
        this.reviewWriteCount = reviewWriteCount;
        this.reviewReceivedCount = reviewReceivedCount;
        this.totalCount = totalCount;
    }
}
