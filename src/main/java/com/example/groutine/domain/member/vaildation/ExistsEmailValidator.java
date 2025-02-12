package com.example.groutine.domain.member.vaildation;

import com.example.groutine.domain.member.service.MemberQueryService;
import com.example.groutine.domain.member.status.MemberErrorStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (memberQueryService.existsByEmail(email)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MemberErrorStatus.EMAIL_ALREADY_EXISTS.getMessage())
                    .addConstraintViolation();
            return false;
        }

        return true;

    }

}
