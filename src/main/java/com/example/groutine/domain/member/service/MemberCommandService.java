package com.example.groutine.domain.member.service;

import com.example.groutine.domain.member.dto.request.MemberInfoRequest;
import com.example.groutine.domain.member.dto.response.MemberIdResponse;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.member.repository.MemberRepository;
import com.example.groutine.domain.member.status.MemberErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import com.example.groutine.global.config.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandService {
    private final MemberRepository memberRepository;

    private final MemberQueryService memberQueryService;
    private final MemberRefreshTokenService refreshTokenService;

    // 회원 저장
    public Member saveEntity(Member member) {
        return memberRepository.save(member);
    }

    public Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RestApiException(MemberErrorStatus.UNAUTHORIZED); // 로그인 안함
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof PrincipalDetails) {
            return ((PrincipalDetails) principal).getMember();
        }

        throw new RestApiException(MemberErrorStatus.AUTHENTICATION_FAILED); // 로그인 정보를 확인할 수 없음

    }

    // 회원가입을 수행하는 함수
    public MemberIdResponse signUp(Member member, MemberInfoRequest request) {
        //이미 소셜 로그인 후, 인증 완료되면 멤버 엔티티는 생겨 있는 상태
        //그 후 추가 정보를 입력받아 저장하는 메서드
        Member loginMember = memberQueryService.findById(member.getId());

        // 기본 정보 저장 로직 작성 필요
        loginMember.setName(request.getName());

        return new MemberIdResponse(saveEntity(loginMember).getId());
    }

    // 회원 탈퇴 함수
    public MemberIdResponse withdrawal(Member member) {
        // 멤버 soft delete
        Member loginMember = memberQueryService.findById(member.getId());

        // refreshToken 삭제
        refreshTokenService.deleteRefreshToken(loginMember);

        // 멤버 soft delete
        loginMember.delete();

        return new MemberIdResponse(loginMember.getId());
    }
}
