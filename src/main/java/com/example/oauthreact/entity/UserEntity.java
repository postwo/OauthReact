package com.example.oauthreact.entity;

import com.example.oauthreact.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
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


    public UserEntity (SignUpRequestDto dto){
        this.userId = dto.getId();
        this.password = dto.getPassword();
        this.email  =  dto.getEmail();
        this.type = "app";
        this.role = "ROLE_USER";
    }
}
