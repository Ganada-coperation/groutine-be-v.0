package com.example.groutine.domain.member.service;

import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.member.entity.LoginType;
import com.example.groutine.domain.member.dto.response.MemberGenerateTokenResponse;
import com.example.groutine.domain.member.dto.response.MemberIdResponse;
import com.example.groutine.domain.member.dto.response.MemberLoginResponse;
import com.example.groutine.domain.member.strategy.context.LoginContext;
import com.example.groutine.global.common.exception.RestApiException;
import com.example.groutine.global.common.exception.code.status.AuthErrorStatus;
import com.example.groutine.global.config.security.jwt.JwtProvider;
import com.example.groutine.global.config.security.jwt.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberAuthCommandService {

    public final MemberService memberService;
    public final MemberRefreshTokenService refreshTokenService;

    public final JwtProvider jwtTokenProvider;
    private final LoginContext loginContext;

    // 소셜 로그인을 수행하는 함수
    public MemberLoginResponse socialLogin(String accessToken, LoginType loginType) {
        return loginContext.executeStrategy(accessToken, loginType);
    }

    // 새로운 액세스 토큰 발급 함수
    public MemberGenerateTokenResponse generateNewAccessToken(String refreshToken, Member member) {

        Member loginMember = memberService.findById(member.getId());

        // 만료된 refreshToken인지 확인
        if (!jwtTokenProvider.validateToken(refreshToken))
            throw new RestApiException(AuthErrorStatus.EXPIRED_REFRESH_TOKEN);

        //편의상 refreshToken을 DB에 저장 후 비교하는 방식으로 감 (비추천)
        String savedRefreshToken = loginMember.getRefreshToken();

        // 디비에 저장된 refreshToken과 동일하지 않다면 유효하지 않음
        if (!refreshToken.equals(savedRefreshToken))
            throw new RestApiException(AuthErrorStatus.INVALID_REFRESH_TOKEN);

        return new MemberGenerateTokenResponse(
                jwtTokenProvider.generateToken(
                        loginMember.getId().toString(), member.getRole().toString(), TokenType.ACCESS)
        );
    }

    // 로그아웃 함수
    public MemberIdResponse logout(Member member) {
        Member loginMember = memberService.findById(member.getId());

        refreshTokenService.deleteRefreshToken(loginMember);
        return new MemberIdResponse(loginMember.getId());
    }

    // 회원 탈퇴 함수
    public MemberIdResponse withdrawal(Member member) {
        // 멤버 soft delete
        Member loginMember = memberService.findById(member.getId());

        // refreshToken 삭제
        refreshTokenService.deleteRefreshToken(loginMember);

        // 멤버 soft delete
        loginMember.delete();

        return new MemberIdResponse(loginMember.getId());
    }
}
