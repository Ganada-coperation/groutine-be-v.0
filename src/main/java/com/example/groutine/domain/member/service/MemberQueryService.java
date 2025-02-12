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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) throws UsernameNotFoundException {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RestApiException(MemberErrorStatus.EMPTY_MEMBER));
    }

}
