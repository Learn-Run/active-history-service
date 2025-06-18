package com.unionclass.activehistoryservice.domain.activehistory.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetActiveHistoryCountResVo {

    private Long postCount;
    private Long commentCount;
    private Long reviewCount;
    private Long totalCount;

    @Builder
    public GetActiveHistoryCountResVo(Long postCount, Long commentCount, Long reviewCount, Long totalCount) {
        this.postCount = postCount;
        this.commentCount = commentCount;
        this.reviewCount = reviewCount;
        this.totalCount = totalCount;
    }
}
