package com.example.oauthreact.controller;

import com.example.oauthreact.dto.request.auth.IdCheckRequestDto;
import com.example.oauthreact.dto.response.auth.IdCheckResponseDto;
import com.example.oauthreact.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (@RequestBody @Valid IdCheckRequestDto requsetbody){
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requsetbody);
        return response;
    }

}
