package com.example.groutine.domain.member.controller;

import com.example.groutine.domain.member.dto.request.MemberInfoRequest;
import com.example.groutine.domain.member.dto.response.MemberIdResponse;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.member.service.MemberCommandService;
import com.example.groutine.domain.member.service.MemberQueryService;
import com.example.groutine.global.common.base.BaseResponse;
import com.example.groutine.global.config.security.auth.CurrentMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Tag(name = "멤버 API", description = "멤버 관련 API")
@RequestMapping("/members")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @Operation(summary = "내 정보 조회 API", description = "내 정보를 조회하는 API입니다.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "COMMON200", description = "성공")
    })
    @GetMapping("")
    public BaseResponse<> getMyInfo(@CurrentMember Member member) {
        return BaseResponse.onSuccess(memberQueryService.getMyInfo(member));
    }

    @Operation(summary = "내 정보 수정 API", description = "내 정보를 수정하는 API입니다.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "COMMON200", description = "성공")
    })
    @PatchMapping("")
    public BaseResponse<> patchMyInfo(@CurrentMember Member member,
                              @Valid @RequestBody MemberInfoRequest request) {
        return BaseResponse.onSuccess(memberCommandService.patchMyInfo(member, request));
    }

    @Operation(summary = "회원가입 API", description = "최초 멤버 정보를 등록하는 API입니다.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "COMMON200", description = "성공")
    })
    @PostMapping
    public BaseResponse<MemberIdResponse> signUp(@CurrentMember Member member,
                                                 @Valid @RequestBody MemberInfoRequest request) {
        return BaseResponse.onSuccess(memberCommandService.signUp(member, request));
    }

    @Operation(summary = "회원 탈퇴 API", description = "해당 유저 정보를 삭제하는 API입니다.")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "COMMON200", description = "성공")
    })
    @DeleteMapping
    public BaseResponse<MemberIdResponse> withdrawal(@CurrentMember Member member) {
        return BaseResponse.onSuccess(memberCommandService.withdrawal(member));
    }

}
