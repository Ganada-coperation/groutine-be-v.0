package com.example.groutine.domain.member.controller;

import com.example.groutine.domain.member.dto.request.EmailRequest;
import com.example.groutine.domain.member.dto.request.VerificationRequest;
import com.example.groutine.domain.member.service.MemberEmailVerificationService;
import com.example.groutine.global.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/email-verification")
@Tag(name = "회원 이메일 인증 API", description = "회원 이메일 인증 관련 API")
public class MemberEmailVerificationController {

    private final MemberEmailVerificationService memberEmailVerificationService;

//    // 인증번호 전송 요청
//    @PostMapping("/send-code")
//    @Operation(summary = "인증번호 전송", description = "회원 이메일로 인증번호를 전송함.")
//    public BaseResponse<> sendVerificationCode(@Valid @RequestBody EmailRequest request) {
//        return BaseResponse.onSuccess(memberEmailVerificationService.sendVerificationEmail(request.getEmail()));
//    }
//
//    // 인증번호 검증 요청
//    @PostMapping("/verify-code")
//    @Operation(summary = "인증번호 검증", description = "회원이 입력한 인증번호를 검증함.")
//    public BaseResponse<> verifyCode(@Valid @RequestBody VerificationRequest request) {
//        return BaseResponse.onSuccess(memberEmailVerificationService.verifyEmail(request));
//    }
}
