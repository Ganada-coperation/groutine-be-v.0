package com.example.groutine.domain.challenge.status;


import com.example.groutine.global.common.exception.code.BaseCodeDto;
import com.example.groutine.global.common.exception.code.BaseCodeInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChallengeErrorStatus implements BaseCodeInterface {
    NOT_FOUND_CHALLENGE(HttpStatus.NOT_FOUND, "CHALLENGE001", "존재하지 않는 챌린지입니다."),
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
