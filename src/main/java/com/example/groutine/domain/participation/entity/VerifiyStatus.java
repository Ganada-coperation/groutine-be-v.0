package com.example.groutine.domain.participation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifiyStatus {

    WAIT("대기"),
    APPROVE("승인"),
    REJECT("거절")
    ;

    private final String status;
}
