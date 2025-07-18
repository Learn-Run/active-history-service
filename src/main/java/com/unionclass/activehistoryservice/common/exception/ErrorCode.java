package com.unionclass.activehistoryservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 600 ~ 699 : gateway service error
     */
    NO_JWT_TOKEN(HttpStatus.UNAUTHORIZED, false, 600, "JWT 토큰이 존재하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, false, 601, "유효하지 않은 JWT 토큰입니다."),

    /**
     * 700 ~ 799 : discovery service error
     */

    /**
     * 800 ~ 899 : internal server error / system, infra error
     */
    LOCK_ACQUISITION_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, false, 800, "락 획득에 실패하였습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 888, "서버에서 요청을 처리하지 못했습니다."),

    /**
     * 900 ~ 999 : validation error
     */

    /**
     * 1000 ~ 1999 : member service error
     */
    // auth : 1000 ~ 1099
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 1001, "로그인을 먼저 진행해주세요"),
    FAILED_TO_SIGN_UP(HttpStatus.INTERNAL_SERVER_ERROR, false, 1002, "회원가입에 실패하였습니다."),
    FAILED_TO_SIGN_IN(HttpStatus.INTERNAL_SERVER_ERROR, false, 1003, "로그인에 실패하였습니다."),

    // member : 1100 ~ 1199
    NO_EXIST_MEMBER(HttpStatus.NOT_FOUND, false, 1100, "해당 회원을 찾을 수 없습니다."),
    INVALID_GENDER_VALUE(HttpStatus.BAD_REQUEST, false, 1101, "유효하지 않은 성별 정보입니다."),
    INVALID_USER_ROLE(HttpStatus.BAD_REQUEST, false, 1102, "유효하지 않은 유저 권한입니다."),
    EMAIL_ENCODING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 1103, "시스템 문자 인코딩 오류로 메일을 보낼 수 없습니다."),
    EMAIL_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, false, 1104, "메일 서버 오류로 인해 전송에 실패했습니다."),
    EMAIL_CODE_INVALID(HttpStatus.BAD_REQUEST, false, 1105, "유효하지 않은 인증코드입니다."),
    EMAIL_CODE_EXPIRED(HttpStatus.BAD_REQUEST, false, 1106, "인증코드가 만료되었습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 1107, "이미 등록된 이메일입니다."),
    LOGIN_ID_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 1108, "이미 등록된 아이디입니다."),
    NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 1109, "이미 등록된 닉네임입니다."),
    INVALID_PASSWORD_RULE(HttpStatus.BAD_REQUEST, false, 1110, "length 에 4 이상의 수를 입력해주세요."),
    INVALID_EMAIL_TITLE_VALUE(HttpStatus.BAD_REQUEST, false, 1111, "유효하지 않은 이메일 제목 정보입니다."),
    INVALID_PASSWORD_LENGTH(HttpStatus.BAD_REQUEST, false, 1112, "비밀번호는 8자 이상 20자 이하로 입력해주세요."),
    INVALID_CURRENT_PASSWORD(HttpStatus.BAD_REQUEST, false, 1113, "현재 비밀번호가 일치하지 않습니다."),
    INVALID_OAUTH_PROVIDER_VALUE(HttpStatus.BAD_REQUEST, false, 1114, "유효하지 않은 OAuth Provider 입니다."),
    NO_EXIST_OAUTH_MEMBER(HttpStatus.NOT_FOUND, false, 1115, "해당 소셜 계정과 연동된 회원이 없습니다. 회원가입을 먼저 진행해주세요."),
    OAUTH_ACCOUNT_ALREADY_BOUND(HttpStatus.CONFLICT, false, 1116, "해당 OAuth 계정은 이미 연동되어 있습니다."),
    OAUTH_PROVIDER_ALREADY_BOUND(HttpStatus.CONFLICT, false, 1117, "해당 회원은 이미 해당 OAuth Provider 와 연동되어 있습니다."),
    FAILED_TO_CREATE_AGREEMENT(HttpStatus.INTERNAL_SERVER_ERROR, false, 1118, "약관동의 항목 생성에 실패하였습니다."),
    FAILED_TO_FIND_AGREEMENT(HttpStatus.BAD_REQUEST, false, 1119, "약관동의 항목 조회에 실패하였습니다."),
    MUST_AGREE_REQUIRED_AGREEMENT(HttpStatus.BAD_REQUEST, false, 1120, "필수 동의 항목에는 반드시 동의해야 합니다."),
    FAILED_TO_FIND_MEMBER_AGREEMENT(HttpStatus.BAD_REQUEST, false, 1121, "회원 약관동의 항목에 대한 정보 조회에 실패하였습니다."),
    CANNOT_UPDATE_REQUIRED_AGREEMENT(HttpStatus.BAD_REQUEST, false, 1122, "필수 동의 항목은 동의 상태를 변경할 수 없습니다."),
    DUPLICATE_TEMPORARY_PASSWORD_REQUEST(HttpStatus.BAD_REQUEST, false, 1123, "이미 임시 비밀번호가 발급된 상태입니다. 일정 시간 후 다시 시도해주세요."),
    FAILED_TO_RESET_PASSWORD(HttpStatus.INTERNAL_SERVER_ERROR, false, 1124, "비밀번호 재설정에 실패하였습니다. 다시 시도 해주세요."),
    FAILED_TO_SAVE_MEMBER(HttpStatus.INTERNAL_SERVER_ERROR, false, 1125, "회원 정보 저장에 실패하였습니다."),
    AUTH_INFORMATION_NOT_FOUND(HttpStatus.NOT_FOUND, false, 1126, "회원 인증 정보를 찾을 수 없습니다."),

    /**
     * 2000 : post service error
     */

    /**
     * 3000 : order service error
     */

    /**
     * 4000 : chat service error
     */

    /**
     * 5000 : notice service error
     */

    /**
     * 6000 : review service error
     */
    // review : 6000 ~ 6099
    FAILED_TO_CREATE_REVIEW(HttpStatus.INTERNAL_SERVER_ERROR, false, 6000, "리뷰 생성에 실패하였습니다."),
    INVALID_RATING_VALUE(HttpStatus.BAD_REQUEST, false, 6001, "유효하지 않은 평점입니다."),
    FAILED_TO_FIND_REVIEW(HttpStatus.NOT_FOUND, false, 6002, "해당 리뷰를 찾을 수 없습니다."),
    FAILED_TO_UPDATE_REVIEW_CONTENTS(HttpStatus.INTERNAL_SERVER_ERROR, false, 6003, "리뷰 내용 수정에 실패하였습니다."),
    FAILED_TO_UPDATE_REVIEW_IMAGES(HttpStatus.INTERNAL_SERVER_ERROR, false, 6004, "리뷰 이미지 수정에 실패하였습니다."),
    FAILED_TO_DELETE_REVIEW(HttpStatus.INTERNAL_SERVER_ERROR, false, 6005, "리뷰 삭제에 실패하였습니다."),
    REVIEW_LOOKUP_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, false, 6006, "리뷰 단건 조회에 실패하였습니다."),

    /**
     * 7000 : active history service error
     */
    // active history : 7000 ~ 7099
    INVALID_ACTIVE_HISTORY_TYPE_VALUE(HttpStatus.BAD_REQUEST, false, 7000, "유효하지 않은 활동이력 유형입니다."),
    FAILED_TO_SAVE_REVIEW_ACTIVE_HISTORY(HttpStatus.INTERNAL_SERVER_ERROR, false, 7001, "리뷰 활동이력 생성 및 저장에 실패하였습니다."),
    FAILED_TO_LOAD_ACTIVE_HISTORY_INFORMATION(HttpStatus.INTERNAL_SERVER_ERROR, false, 7002, "활동이력 정보 조회에 실패하였습니다."),
    INVALID_PERIOD_VALUE(HttpStatus.BAD_REQUEST, false, 7003, "유효하지 않은 기간 타입입니다."),
    FAILED_TO_GET_ACTIVE_HISTORY_COUNT(HttpStatus.INTERNAL_SERVER_ERROR, false, 7004, "활동이력 개수 조회에 실패하였습니다."),
    FAILED_TO_SAVE_POST_ACTIVE_HISTORY(HttpStatus.INTERNAL_SERVER_ERROR, false, 7005, "질문 활동이력 생성 및 저장에 실패하였습니다."),
    FAILED_TO_SAVE_COMMENT_ACTIVE_HISTORY(HttpStatus.INTERNAL_SERVER_ERROR, false, 7006, "댓글 활동이력 생성 및 저장에 실패하였습니다."),
    FAILED_TO_FIND_ACTIVE_HISTORY_BY_MEMBER_UUID_AND_COMMENT_UUID(HttpStatus.BAD_REQUEST, false, 7007, "회원 UUID 와 댓글 UUID 로 활동 이력을 조회하는데 실패하였습니다."),
    FAILED_TO_UPDATED_ACTIVE_HISTORY_DELETED_STATUS(HttpStatus.INTERNAL_SERVER_ERROR, false, 7008, "활동 이력 삭제 상태 변경에 실패하였습니다."),
    FAILED_TO_FIND_ACTIVE_HISTORY_BY_POST_UUID(HttpStatus.BAD_REQUEST, false, 7009, "질문 UUID 로 활동 이력을 조회하는데 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
