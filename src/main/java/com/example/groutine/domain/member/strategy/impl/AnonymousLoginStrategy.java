package com.example.groutine.domain.member.strategy.impl;


import com.example.groutine.domain.member.entity.LoginType;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.member.entity.Role;
import com.example.groutine.domain.member.dto.response.MemberLoginResponse;
import com.example.groutine.domain.member.mapper.MemberMapper;
import com.example.groutine.domain.member.repository.MemberRepository;
import com.example.groutine.domain.member.service.MemberCommandService;
import com.example.groutine.domain.member.service.MemberQueryService;
import com.example.groutine.domain.member.strategy.LoginStrategy;
import com.example.groutine.global.config.security.jwt.JwtProvider;
import com.example.groutine.global.config.security.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AnonymousLoginStrategy implements LoginStrategy {

    private final MemberRepository memberRepository;
    private final MemberCommandService memberCommandService;
    private final JwtProvider jwtProvider;

    @Override
    public MemberLoginResponse login(String accessToken) {
        // Anonymous-specific logic
        Optional<Member> getMember = Optional.ofNullable(memberRepository.findByClientIdAndLoginType(accessToken, LoginType.ANONYMOUS));

        if (getMember.isEmpty()) {
            return saveNewMember(accessToken, LoginType.ANONYMOUS);
        }

        Member member = getMember.get();
        boolean isServiceMember = member.getName() != null;
        TokenInfo tokenInfo = generateToken(member);

        return MemberMapper.toLoginMember(member, tokenInfo, isServiceMember, member.getRole());
    }

    private MemberLoginResponse saveNewMember(String clientId, LoginType loginType) {
        Member member = MemberMapper.toMember(clientId, loginType);
        member.changeRole(Role.GUEST);
        Member newMember = memberCommandService.saveEntity(member);
        TokenInfo tokenInfo = generateToken(newMember);
        return MemberMapper.toLoginMember(newMember, tokenInfo, false, Role.GUEST);
    }

    private TokenInfo generateToken(Member member) {
        return jwtProvider.generateToken(member.getId().toString(), member.getRole().toString());
    }
}

