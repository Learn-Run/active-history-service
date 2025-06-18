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
    private Long reviewCount;
    private Long totalCount;

    @Builder
    public GetActiveHistoryCountResDto(Long postCount, Long commentCount, Long reviewCount, Long totalCount) {
        this.postCount = postCount;
        this.commentCount = commentCount;
        this.reviewCount = reviewCount;
        this.totalCount = totalCount;
    }

    public static GetActiveHistoryCountResDto of(Long postCount, Long commentCount, Long reviewCount, Long totalCount) {
        return GetActiveHistoryCountResDto.builder()
                .postCount(postCount)
                .commentCount(commentCount)
                .reviewCount(reviewCount)
                .totalCount(totalCount)
                .build();
    }

    public GetActiveHistoryCountResVo toVo() {
        return GetActiveHistoryCountResVo.builder()
                .postCount(postCount)
                .commentCount(commentCount)
                .reviewCount(reviewCount)
                .totalCount(totalCount)
                .build();
    }
}
