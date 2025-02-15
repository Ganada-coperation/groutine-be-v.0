package com.example.groutine.domain.member.dto.request;

import com.example.groutine.domain.member.vaildation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VerificationRequest(
        @Email
        String email,
        String verificationCode
) {
}
