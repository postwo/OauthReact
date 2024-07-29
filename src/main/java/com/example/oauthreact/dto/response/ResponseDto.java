package com.example.oauthreact.dto.response;

import com.example.oauthreact.common.ResponseCode;
import com.example.oauthreact.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;
    
    public ResponseDto(){
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    //에러
    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR,ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }


    //검증 에러
    public static ResponseEntity<ResponseDto> validation(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAIL,ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }



}
