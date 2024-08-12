package com.example.oauthreact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthReactApplication.class, args);

        System.out.println("Kakao ID: " + System.getenv("kid"));
        System.out.println("Kakao Secret: " + System.getenv("kpw"));
        System.out.println("Naver ID: " + System.getenv("naverId"));
        System.out.println("Naver Secret: " + System.getenv("naverPw"));
    }

}
