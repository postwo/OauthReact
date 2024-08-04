package com.example.oauthreact.dto.response.auth;

import com.example.oauthreact.common.ResponseCode;
import com.example.oauthreact.common.ResponseMessage;
import com.example.oauthreact.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignupResponseDto extends ResponseDto {

    private SignupResponseDto(){
        super();
    }

    public static ResponseEntity<SignupResponseDto> success (){
        SignupResponseDto responsebody = new SignupResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }

    public static ResponseEntity<ResponseDto> duplicateId () {
        ResponseDto responsebody = new ResponseDto(ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsebody);
    }

    public static ResponseEntity<ResponseDto> certificationFail () {
        ResponseDto responsebody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);
    }

}
