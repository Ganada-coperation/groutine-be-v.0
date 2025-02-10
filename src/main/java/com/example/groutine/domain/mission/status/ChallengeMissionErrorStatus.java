package com.example.groutine.domain.mission.status;

import com.example.groutine.global.common.exception.code.BaseCodeDto;
import com.example.groutine.global.common.exception.code.BaseCodeInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChallengeMissionErrorStatus implements BaseCodeInterface {
    NOT_PARTICIPATED_CHALLENGE(HttpStatus.BAD_REQUEST, "CPE001", "참여하지 않은 챌린지 입니다."),
    ALREADY_PARTICIPATED_CHALLENGE(HttpStatus.BAD_REQUEST, "CPE002", "이미 참여한 챌린지 입니다."),
    VERIFICATION_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "CPE003", "인증 게시글을 찾을 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final boolean isSuccess = false;
    private final String code;
    private final String message;

    @Override
    public BaseCodeDto getCode() {
        return BaseCodeDto.builder()
                .httpStatus(httpStatus)
                .isSuccess(isSuccess)
                .code(code)
                .message(message)
                .build();
    }
}

