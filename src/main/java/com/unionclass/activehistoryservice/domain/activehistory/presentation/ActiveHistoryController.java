package com.unionclass.activehistoryservice.domain.activehistory.presentation;

import com.unionclass.activehistoryservice.common.response.BaseResponseEntity;
import com.unionclass.activehistoryservice.common.response.CursorPage;
import com.unionclass.activehistoryservice.common.response.ResponseMessage;
import com.unionclass.activehistoryservice.domain.activehistory.application.ActiveHistoryService;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryCountReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.in.GetActiveHistoryReqDto;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import com.unionclass.activehistoryservice.domain.activehistory.enums.Period;
import com.unionclass.activehistoryservice.domain.activehistory.vo.out.GetActiveHistoryCountResVo;
import com.unionclass.activehistoryservice.domain.activehistory.vo.out.GetActiveHistoryResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/active-history")
@Tag(name = "active-history")
public class ActiveHistoryController {

    private final ActiveHistoryService activeHistoryService;

    /**
     * 1. 활동 이력 조회
     *
     * @param memberUuid
     * @param cursorId
     * @param page
     * @param size
     * @param type
     * @return
     */
    @Operation(
            summary = "활동 이력 조회",
            description = """
                    특정 회원의 활동 이력을 커서 기반 페이지네이션으로 조회합니다.
                    
                    [요청 경로]
                    - PathVariable : (String) memberUuid - 조회 대상 회원 UUID
                    
                    [요청 파라미터]
                    - cursorId : (String, 선택 입력) 커서 기준 ID (기본값 : null)
                    - page : (int, 선택 입력) 현재 페이지 번호 (기본값 : 0)
                    - size : (int, 선택 입력) 페이지당 조회 수 (기본값 : 9)
                    - type : (ActiveHistoryType, 선택입력) : 필터링할 활동 이력 타입, 미입력시 전체 조회 (POST, COMMENT, REVIEW)
                    
                    [처리 로직]
                    - cursorId 가 있으면 해당 커서부터 size 만큼 이력 조회
                    - cursorId 가 없으면 offset 기반 커서를 구해서 해당 커서로 부터 size 만큼 이력 조회
                    
                    [예외 상황]
                    - FAILED_TO_LOAD_ACTIVE_HISTORY_INFORMATION: 활동이력 조회 중 예기치 못한 오류 발생
                    """
    )
    @GetMapping("/{memberUuid}")
    public BaseResponseEntity<CursorPage<GetActiveHistoryResVo>> getActiveHistory(
            @PathVariable String memberUuid,
            @RequestParam(required = false) String cursorId,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "9") int size,
            @RequestParam(required = false, defaultValue = "REVIEW") ActiveHistoryType type
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_ACTIVE_HISTORY_INFORMATION.getMessage(),
                activeHistoryService.getActiveHistory(GetActiveHistoryReqDto
                        .of(memberUuid, cursorId, page, size, type)).map(GetActiveHistoryResDto::toVo));
    }

    /**
     * 2. 활동이력 갯수 조회
     *
     * @param memberUuid
     * @param period
     * @return
     */
    @Operation(
            summary = "활동 이력 개수 조회",
            description = """
                    특정 회원의 활동 이력의 개수를 조회합니다.
                    
                    [요청 경로]
                    - PathVariable : (String) memberUuid - 조회 대상 회원 UUID
                    
                    [요청 파라미터]
                    - period : (필수 입력) 기간 (TOTAL, TODAY)
                    
                    [처리 로직]
                    - period 값에 따라 해당 회원의 활동 내역 개수를 조회
                      * today: 금일 기준 (00:00 ~ 23:59:59 범위) 생성된 활동 이력만 집계
                      * total: 전체 활동 이력 집계
                    
                    [응답 바디]
                    - postCount : 게시글 작성 활동 개수
                    - commentCount : 댓글 작성 활동 개수
                    - reviewCount : 리뷰 작성 활동 개수
                    - totalCount : 총 활동 개수
                    
                    [예외 상황]
                    - FAILED_TO_GET_ACTIVE_HISTORY_COUNT: 활동이력 개수 조회 중 예기치 못한 오류 발생
                    
                    [참고 사항]
                    - 현재 빠르게 개발하느라 switch 문을 통해 개발하였지만,
                    - 추후 확장성과 유지보수성을 위해 조건 기반 처리(criteria 방식)로 리팩토링할 예정입니다.
                    """
    )
    @GetMapping("/{memberUuid}/count")
    public BaseResponseEntity<GetActiveHistoryCountResVo> getActiveHistoryCount(
            @PathVariable String memberUuid,
            @RequestParam Period period
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_ACTIVE_HISTORY_COUNT.getMessage(),
                activeHistoryService.getActiveHistoryCount(GetActiveHistoryCountReqDto.of(memberUuid, period)).toVo());
    }
}
