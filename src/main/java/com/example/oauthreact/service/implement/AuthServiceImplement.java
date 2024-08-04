package com.example.oauthreact.service.implement;

import com.example.oauthreact.common.CertificationNumber;
import com.example.oauthreact.dto.request.auth.EmailCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.IdCheckRequestDto;
import com.example.oauthreact.dto.response.ResponseDto;
import com.example.oauthreact.dto.response.auth.EmailCertificationReponseDto;
import com.example.oauthreact.dto.response.auth.IdCheckResponseDto;
import com.example.oauthreact.entity.CertificationEntity;
import com.example.oauthreact.provider.EmailProvider;
import com.example.oauthreact.repository.CertificationRepository;
import com.example.oauthreact.repository.UserRepository;
import com.example.oauthreact.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;

    private final CertificationRepository certificationRepository;

    private final EmailProvider emailProvider;


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


    @Override
    public ResponseEntity<? super EmailCertificationReponseDto> emailCertification(EmailCertificationRequestDto dto) {
        try{

            String userId = dto.getId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsById(userId);
            if (isExistId) return EmailCertificationReponseDto.duplicateId();

            String certificationNumber = CertificationNumber.getCertificationNumber(); //0~9 사이에 수 를 받아온다

            boolean isSuccessed = emailProvider.sendCertificationMail(email,certificationNumber);
            if (!isSuccessed) return EmailCertificationReponseDto.mailsendFail(); //false 일 경우

            CertificationEntity certificationEntity = new CertificationEntity(userId,email,certificationNumber);
            certificationRepository.save(certificationEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return EmailCertificationReponseDto.success();
    }

}
