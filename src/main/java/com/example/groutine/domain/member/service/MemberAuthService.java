package com.example.groutine.domain.member.service;

import com.example.groutine.domain.member.dto.request.MemberLoginRequest;
import com.example.groutine.domain.member.dto.request.MemberSignInRequest;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.member.entity.LoginType;
import com.example.groutine.domain.member.dto.response.MemberGenerateTokenResponse;
import com.example.groutine.domain.member.dto.response.MemberIdResponse;
import com.example.groutine.domain.member.dto.response.MemberLoginResponse;
import com.example.groutine.domain.member.entity.Role;
import com.example.groutine.domain.member.mapper.MemberMapper;
import com.example.groutine.domain.member.strategy.context.LoginContext;
import com.example.groutine.global.common.exception.RestApiException;
import com.example.groutine.global.common.exception.code.status.AuthErrorStatus;
import com.example.groutine.global.config.security.jwt.JwtProvider;
import com.example.groutine.global.config.security.jwt.TokenInfo;
import com.example.groutine.global.config.security.jwt.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberAuthService {

    public final MemberQueryService memberQueryService;
    public final MemberCommandService memberCommandService;
    public final MemberRefreshTokenService refreshTokenService;

    public final JwtProvider jwtProvider;
    private final LoginContext loginContext;
    private final PasswordEncoder passwordEncoder;

    // 소셜 로그인을 수행하는 함수
    public MemberLoginResponse socialLogin(String accessToken, LoginType loginType) {
        // todo 정보 등록 완료한 유저인지 알아야 함

        return loginContext.executeStrategy(accessToken, loginType);
    }

    // 새로운 액세스 토큰 발급 함수
    public MemberGenerateTokenResponse generateNewAccessToken(String refreshToken, Member member) {

        Member loginMember = memberQueryService.findById(member.getId());

        // 만료된 refreshToken인지 확인
        if (!jwtProvider.validateToken(refreshToken))
            throw new RestApiException(AuthErrorStatus.EXPIRED_REFRESH_TOKEN);

        //편의상 refreshToken을 DB에 저장 후 비교하는 방식으로 감 (비추천)
        String savedRefreshToken = loginMember.getRefreshToken();

        // 디비에 저장된 refreshToken과 동일하지 않다면 유효하지 않음
        if (!refreshToken.equals(savedRefreshToken))
            throw new RestApiException(AuthErrorStatus.INVALID_REFRESH_TOKEN);

        return new MemberGenerateTokenResponse(
                jwtProvider.generateToken(
                        loginMember.getId().toString(), member.getRole().toString(), TokenType.ACCESS),
                jwtProvider.generateToken(
                        loginMember.getId().toString(), member.getRole().toString(), TokenType.REFRESH)
        );
    }

    // 로그아웃 함수
    public MemberIdResponse logout(Member member) {
        Member loginMember = memberQueryService.findById(member.getId());

        refreshTokenService.deleteRefreshToken(loginMember);
        return new MemberIdResponse(loginMember.getId());
    }

    // 자체 로그인 함수 todo : loginContext.executeStrategy(request.email(), request.password());
    public MemberLoginResponse login(MemberLoginRequest request) {
        Member member = memberQueryService.findByEmail(request.email());
        if (!passwordEncoder.matches(request.password(), member.getPassword()))
            throw new RestApiException(AuthErrorStatus.INVALID_PASSWORD);

        // todo 정보 등록 완료한 유저인지 알아야 함

        TokenInfo tokenInfo = generateToken(member);
        return MemberMapper.toLoginMember(member, tokenInfo, true, member.getRole());
    }

    // 회원가입 함수
    public MemberLoginResponse signUp(MemberSignInRequest request) {
        // 회원가입 후 토큰 발급 todo : loginContext.executeStrategy(request.email(), request.password());
        return saveNewMember(request.email(), passwordEncoder.encode(request.password()));
    }

    // todo 모듈화 시키기
    private MemberLoginResponse saveNewMember(String email, String password) {
        Member member = MemberMapper.toMember(email, password);
        member.changeRole(Role.GUEST);
        Member newMember = memberCommandService.saveEntity(member);
        TokenInfo tokenInfo = generateToken(newMember);
        return MemberMapper.toLoginMember(newMember, tokenInfo, false, Role.GUEST);
    }

    private TokenInfo generateToken(Member member) {
        return jwtProvider.generateToken(member.getId().toString(), member.getRole().toString());
    }
}
