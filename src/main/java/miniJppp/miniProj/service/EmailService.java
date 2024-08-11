package miniJppp.miniProj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private String authNum; // 인증 번호

    // d 8자리 무작위 생성
    public void createCode() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        StringBuilder tempPw = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            tempPw.append(charSet[idx]);
        }
        authNum = String.valueOf(tempPw);
    }

    // 메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
        createCode();
        String setFrom = "minijppp@gmail.com";
        String toEmail = email;
        String title = "minijppp 임시 비밀번호 발급";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, toEmail);
        message.setSubject(title);

        // 메일 내용
        String msgOfEmail="";
        msgOfEmail += "<div style='margin:20px;'>";
        msgOfEmail += "<h1> 안녕하세요 minijppp 입니다. </h1>";
        msgOfEmail += "<br>";
        msgOfEmail += "<p>로그인 후 꼭 비밀번호를 변경하시기 바랍니다.</p>";
        msgOfEmail += "<br>";
        msgOfEmail += "<p>감사합니다.<p>";
        msgOfEmail += "<br>";
        msgOfEmail += "<p>임시 비밀번호: ";
        msgOfEmail += authNum;
        msgOfEmail += "</p>";

        message.setFrom(setFrom);
        message.setText(msgOfEmail, "utf-8", "html");

        return message;
    }

    //실제 메일 전송
    public String sendEmail(String email) throws MessagingException, UnsupportedEncodingException {

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(email);
        //실제 메일 전송
        emailSender.send(emailForm);

        return authNum; //인증 코드 반환
    }
}
