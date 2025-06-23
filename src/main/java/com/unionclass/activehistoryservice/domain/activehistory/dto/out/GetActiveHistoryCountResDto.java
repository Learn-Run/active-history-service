package com.unionclass.activehistoryservice.domain.activehistory.dto.out;

import com.unionclass.activehistoryservice.domain.activehistory.vo.out.GetActiveHistoryCountResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetActiveHistoryCountResDto {

    private Long postCount;
    private Long commentCount;
    private Long reviewWriteCount;
    private Long reviewReceivedCount;
    private Long totalCount;

    @Builder
    public GetActiveHistoryCountResDto(
            Long postCount, Long commentCount, Long reviewWriteCount, Long reviewReceivedCount, Long totalCount
    ) {
        this.postCount = postCount;
        this.commentCount = commentCount;
        this.reviewWriteCount = reviewWriteCount;
        this.reviewReceivedCount = reviewReceivedCount;
        this.totalCount = totalCount;
    }

    public static GetActiveHistoryCountResDto of(
            Long postCount, Long commentCount, Long reviewWriteCount, Long reviewReceivedCount, Long totalCount
    ) {
        return GetActiveHistoryCountResDto.builder()
                .postCount(postCount)
                .commentCount(commentCount)
                .reviewWriteCount(reviewWriteCount)
                .reviewReceivedCount(reviewReceivedCount)
                .totalCount(totalCount)
                .build();
    }

    public GetActiveHistoryCountResVo toVo() {
        return GetActiveHistoryCountResVo.builder()
                .postCount(postCount)
                .commentCount(commentCount)
                .reviewWriteCount(reviewWriteCount)
                .reviewReceivedCount(reviewReceivedCount)
                .totalCount(totalCount)
                .build();
    }
}
