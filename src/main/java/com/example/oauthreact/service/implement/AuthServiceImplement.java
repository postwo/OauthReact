package com.example.oauthreact.service.implement;

import com.example.oauthreact.common.CertificationNumber;
import com.example.oauthreact.dto.request.auth.CheckCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.EmailCertificationRequestDto;
import com.example.oauthreact.dto.request.auth.IdCheckRequestDto;
import com.example.oauthreact.dto.request.auth.SignUpRequestDto;
import com.example.oauthreact.dto.response.ResponseDto;
import com.example.oauthreact.dto.response.auth.CheckCertificationResponseDto;
import com.example.oauthreact.dto.response.auth.EmailCertificationReponseDto;
import com.example.oauthreact.dto.response.auth.IdCheckResponseDto;
import com.example.oauthreact.dto.response.auth.SignupResponseDto;
import com.example.oauthreact.entity.CertificationEntity;
import com.example.oauthreact.entity.UserEntity;
import com.example.oauthreact.provider.EmailProvider;
import com.example.oauthreact.repository.CertificationRepository;
import com.example.oauthreact.repository.UserRepository;
import com.example.oauthreact.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;

    private final CertificationRepository certificationRepository;

    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


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

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        try{

            String userId = dto.getId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);

            if (certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) return CheckCertificationResponseDto.certificationFail(); //false일경우

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }


    //회원가입
    @Override
    public ResponseEntity<? super SignupResponseDto> signup(SignUpRequestDto dto) {
        try {
        String userId = dto.getId();
        boolean isExistId = userRepository.existsByUserId(userId);
        if (isExistId) return SignupResponseDto.duplicateId(); //존재할경우

        String email = dto.getEmail();
        String certificationNumber = dto.getCertificationNumber();
        CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
        boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
        if (!isMatched) return SignupResponseDto.certificationFail();

        String password = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        dto.setPassword(encodedPassword);

        UserEntity userEntity = new UserEntity(dto);
        userRepository.save(userEntity);

        //둘중에 아무거나 사용해도 된다
//        certificationRepository.delete(certificationEntity);
        certificationRepository.deleteByUserId(userId);


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignupResponseDto.success();
    }


}
