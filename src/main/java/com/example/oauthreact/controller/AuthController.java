package com.example.oauthreact.controller;

import com.example.oauthreact.dto.request.auth.CheckCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.EmailCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.IdCheckRequestDto;
import com.example.oauthreact.dto.request.auth.SignUpRequestDto;
import com.example.oauthreact.dto.response.auth.CheckCertificationResponseDto;
import com.example.oauthreact.dto.response.auth.EmailCertificationReponseDto;
import com.example.oauthreact.dto.response.auth.IdCheckResponseDto;
import com.example.oauthreact.dto.response.auth.SignupResponseDto;
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
        log.info("나 왔어요");
        ResponseEntity<? super EmailCertificationReponseDto> response = authService.emailCertification(requestbody);
        log.info("======"+response);
        log.info("지금메일 보냈어요");
        return response;
    }


    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto requestbody
    ){
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

}
