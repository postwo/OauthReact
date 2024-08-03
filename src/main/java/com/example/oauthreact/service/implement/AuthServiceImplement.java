package com.example.oauthreact.service.implement;

import com.example.oauthreact.dto.request.auth.IdCheckRequestDto;
import com.example.oauthreact.dto.response.ResponseDto;
import com.example.oauthreact.dto.response.auth.IdCheckResponseDto;
import com.example.oauthreact.repository.UserRepository;
import com.example.oauthreact.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;


    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try{
            String userId = dto.getId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return IdCheckResponseDto.duplicateId(); // true일경우

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }

}
