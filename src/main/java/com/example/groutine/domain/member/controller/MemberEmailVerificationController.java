package com.example.groutine.domain.member.controller;

import com.example.groutine.global.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members/email-verification")
@Tag(name = "회원 이메일 인증 API", description = "회원 이메일 인증 관련 API")
public class MemberEmailVerificationController {

    // 인증번호 전송 요청
    @PostMapping("/send-code")
    @Operation(summary = "인증번호 전송", description = "회원 이메일로 인증번호를 전송함.")
    public BaseResponse<> sendVerificationCode(@RequestBody EmailRequestDto requestDto) {
        // 인증번호 생성 및 이메일 전송 로직 호출
        return BaseResponse.onSuccess();
    }

    // 인증번호 검증 요청
    @PostMapping("/verify-code")
    @Operation(summary = "인증번호 검증", description = "회원이 입력한 인증번호를 검증함.")
    public BaseResponse<> verifyCode(@RequestBody VerificationRequestDto requestDto) {
        // 인증번호 검증 로직 호출
        return BaseResponse.onSuccess();
    }
}
