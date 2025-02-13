package com.example.groutine.domain.member.entity;

import com.example.groutine.domain.challenge.entity.Challenge;
import com.example.groutine.domain.challenge.entity.ChallengeMember;
import com.example.groutine.domain.member.dto.request.MemberInfoRequest;
import com.example.groutine.domain.mission.entity.ChallengeMissionVerification;
import com.example.groutine.global.common.base.BaseEntity;
import com.example.groutine.global.common.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Setter
    private String name;

    @Setter
    private String profileImageUrl;

    @Setter
    private LocalDateTime birth;

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
    private List<ChallengeMember> challengeMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Challenge> challengeList = new ArrayList<>();


    @Builder
    public Member(String name, LoginType loginType, String clientId, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.MEMBER;
        this.loginType = loginType;
        this.clientId = clientId;
        this.status = Status.ACTIVE;
    }

    public void updateMember(MemberInfoRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.profileImageUrl = request.profileImageUrl();
        this.birth = request.birth();
        this.gender = request.gender();
    }

    public void changeRole(Role role) {
        this.role = role;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


}