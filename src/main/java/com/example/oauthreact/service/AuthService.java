package com.example.oauthreact.service;

import com.example.oauthreact.dto.request.auth.*;
import com.example.oauthreact.dto.response.auth.*;
import org.springframework.http.ResponseEntity;


public interface AuthService {

    //부모 형태도 가져올수 있게 이렇게 제네릭을 표현함
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    //이메일
    ResponseEntity<? super EmailCertificationReponseDto> emailCertification(EmailCertificationRequestDto dto);

    //메일 인증 코드 확인
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

    //회원가입
    ResponseEntity<? super SignupResponseDto> signup(SignUpRequestDto dto);

    //로그인
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);


}
