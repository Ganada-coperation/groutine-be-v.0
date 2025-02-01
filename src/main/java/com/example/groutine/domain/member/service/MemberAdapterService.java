package com.example.groutine.domain.member.service;

import com.example.groutine.domain.member.entity.Member;

public interface MemberAdapterService {
    // todo: 의존성 분리하기
    Member getMemberByPhone(String phone);
    boolean existsByPhone(String phone);
}
