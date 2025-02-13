package com.example.groutine.domain.member.dto.request;

import com.example.groutine.domain.member.entity.LoginType;
import org.springframework.web.bind.annotation.RequestParam;

public record SocialLoginRequest(
        String accessToken,
        LoginType loginType
) {
}
