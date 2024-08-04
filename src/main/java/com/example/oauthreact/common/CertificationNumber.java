package com.example.oauthreact.common;

public class CertificationNumber {

    public static String getCertificationNumber(){

        String certificationNumber = " ";

        for (int count = 0; count<4; count++) certificationNumber += (int) (Math.random() * 10); //0~9 사이의 숫자를 임의로 받아온다

        return certificationNumber;
    }

}
