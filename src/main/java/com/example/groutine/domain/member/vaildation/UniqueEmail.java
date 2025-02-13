package com.example.groutine.domain.member.vaildation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsEmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "이미 존재하는 이메일입니다.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
