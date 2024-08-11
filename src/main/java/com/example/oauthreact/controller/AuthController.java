package com.example.oauthreact.controller;

import com.example.oauthreact.dto.request.auth.*;
import com.example.oauthreact.dto.response.auth.*;
import com.example.oauthreact.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (@RequestBody @Valid IdCheckRequestDto requsetbody){
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requsetbody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationReponseDto> emailCertification (
            @RequestBody @Valid EmailCertificationRequestDto requestbody
    ){
        ResponseEntity<? super EmailCertificationReponseDto> response = authService.emailCertification(requestbody);
        return response;
    }


    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto requestbody
    ){
        //서버는 문제가 없다 react 단에서 문제다 = 인증번호 틀릴시 메시지 띄우는게 문제
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestbody);
        return response;
    }


    //회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignupResponseDto> signup(
            @RequestBody @Valid SignUpRequestDto requestbody
            ){
        ResponseEntity<? super SignupResponseDto> response = authService.signup(requestbody);
        return response;
    }


    //로그인
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn (
            @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response =authService.signIn(requestBody);
        return response;
    }

}
