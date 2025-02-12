package com.example.groutine.domain.member.service.adapter;

import com.example.groutine.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberAdapterServiceImpl implements MemberAdapterService{

    private final MemberQueryService memberQueryService;
}
