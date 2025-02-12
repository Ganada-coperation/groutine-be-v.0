package com.example.groutine.domain.member.service;


import com.example.groutine.domain.member.dto.response.MemberInfoResponse;
import com.example.groutine.domain.member.entity.Member;
import com.example.groutine.domain.member.mapper.MemberMapper;
import com.example.groutine.domain.member.repository.MemberRepository;
import com.example.groutine.domain.member.status.MemberErrorStatus;
import com.example.groutine.global.common.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) throws UsernameNotFoundException {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RestApiException(MemberErrorStatus.EMPTY_MEMBER));
    }

    // 멤버 정보를 가져오는 함수
    public MemberInfoResponse getMemberInfo(Member member){
        return MemberMapper.toMemberInfoResponse(member);
    }

    // 이메일이 존재하는지 확인하는 함수
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

}
