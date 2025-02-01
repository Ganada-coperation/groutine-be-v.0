package com.example.groutine.domain.member.service;

import com.example.groutine.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAdapterServiceImpl implements MemberAdapterService{

    private final MemberService memberService;

    @Override
    public Member getMemberByPhone(String phone) {
        return memberService.getMemberByPhone(phone);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return memberService.existsByPhone(phone);
    }

}
