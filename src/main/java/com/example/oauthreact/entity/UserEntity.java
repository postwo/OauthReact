package com.example.oauthreact.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
//@Table(name=) 애노테이션은 데이터베이스에 이미 생성된 테이블과 자바 클래스(엔티티 클래스)를 연동하는 데 사용
public class UserEntity {
    @Id
    private String userId;

    private String password;

    private String email;

    private String type;

    private String role;
}
