package com.example.groutine.domain.member.dto.request;

import com.example.groutine.domain.member.vaildation.UniqueEmail;
import jakarta.validation.constraints.Email;

public record MemberSignInRequest(
        @Email
        @UniqueEmail
        String email,
        String password
) {
}
