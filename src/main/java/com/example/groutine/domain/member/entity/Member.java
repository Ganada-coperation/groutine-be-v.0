package com.example.groutine.domain.member.entity;

import com.example.groutine.domain.participation.entity.MemberChallenge;
import com.example.groutine.domain.participation.entity.MissionVerification;
import com.example.groutine.global.common.base.BaseEntity;
import com.example.groutine.global.common.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    private String profileImageUrl;

    @Setter
    private String birth;

    @Setter
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String clientId;

    // todo 편의상 DB에 저장, 실제로는 저장하지 않게 해야 함
    @Setter
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private MemberLoginInfo memberLoginInfo;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberChallenge> memberChallengeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MissionVerification> missionVerificationList = new ArrayList<>();


    @Builder
    public Member(String name, String email, LoginType loginType, String clientId) {
        this.name = name;
        this.role = Role.MEMBER;
        this.loginType = loginType;
        this.clientId = clientId;
        this.status = Status.ACTIVE;
    }

    public void changeRole(Role role) {
        this.role = role;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}