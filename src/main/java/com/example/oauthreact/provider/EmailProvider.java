package com.example.oauthreact.provider;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailProvider {

    private final JavaMailSender javaMailSender;
    private final String SUBJECT = "[임대주택 가격 서비스] 인증메일 입니다.";

    public boolean sendCertificationMail(String email, String certificationNumber){
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email); //어디로 메일을 보낼거냐
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent,true);//ture는 html을 적용할거냐 말거냐를 묻는거다

            javaMailSender.send(message);//전송을 해준다

        } catch (Exception e) {
            e.printStackTrace();
            log.error("메일 전송 실패: {}", e.getMessage());
            return false;
        }

        return true;

    }

    private String getCertificationMessage(String certificationNumber){

        String certificationMessage = "" ;
        certificationMessage += "<h1 style='text-align: center;'>[임대주택 가격 서비스]  인증메일</h1>";
        certificationMessage += "<h3 style='text-align: center;'> 인증코드 : <strong style= 'font-size: 32px; letter-spacing: 8px;'>" +
                certificationNumber +"</strong></h3>";
        return certificationMessage;
    }

}
