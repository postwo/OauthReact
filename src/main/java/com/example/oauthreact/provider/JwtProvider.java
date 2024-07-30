package com.example.oauthreact.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${secret-key}") //yml파일에 있는 키값을 가지고 온다
    private String secretKey;

    //jwt 생성 메소드
    public String create(String userId) {

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));//추가한 날짜 , //1시간    //만료기간

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));// 시크릿키를 만든다

        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();
        return jwt;

    }


    //jwt 검증
    public String Validate(String jwt){

       String subject = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try{
            subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

        return subject;
    }

}
