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
    VERIFICATION_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "CPE003", "인증 게시글을 찾을 수 없습니다."),
    NOT_PARTICIPATED_MISSION(HttpStatus.BAD_REQUEST, "CPE004", "참여하지 않은 미션 입니다."),
    ALREADY_PARTICIPATED_MISSION(HttpStatus.BAD_REQUEST, "CPE005", "이미 참여한 미션 입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "CPE006", "미션을 찾을 수 없습니다."),
    CHALLENGE_NOT_STARTED(HttpStatus.BAD_REQUEST, "CPE007", "아직 시작하지 않은 챌린지 입니다."),
    CHALLENGE_FINISHED(HttpStatus.BAD_REQUEST, "CPE008", "이미 종료된 챌린지 입니다."),
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

