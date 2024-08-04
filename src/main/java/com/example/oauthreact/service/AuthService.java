package com.example.oauthreact.service;

import com.example.oauthreact.dto.request.auth.CheckCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.EmailCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.IdCheckRequestDto;
import com.example.oauthreact.dto.request.auth.SignUpRequestDto;
import com.example.oauthreact.dto.response.auth.CheckCertificationResponseDto;
import com.example.oauthreact.dto.response.auth.EmailCertificationReponseDto;
import com.example.oauthreact.dto.response.auth.IdCheckResponseDto;
import com.example.oauthreact.dto.response.auth.SignupResponseDto;
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


}
