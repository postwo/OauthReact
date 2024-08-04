package com.example.oauthreact.service;

import com.example.oauthreact.dto.request.auth.EmailCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.IdCheckRequestDto;
import com.example.oauthreact.dto.response.auth.EmailCertificationReponseDto;
import com.example.oauthreact.dto.response.auth.IdCheckResponseDto;
import org.springframework.http.ResponseEntity;


public interface AuthService {

    //부모 형태도 가져올수 있게 이렇게 제네릭을 표현함
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    //이메일
    ResponseEntity<? super EmailCertificationReponseDto> emailCertification(EmailCertificationRequestDto dto);





}
