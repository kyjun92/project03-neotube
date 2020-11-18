package com.mega.mvc01.cooking;

import javax.mail.internet.MimeMessage;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Aspect
@Service("mail")
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void mailSend(JoinPoint joinPint, Throwable ex) { // servlet-context에 있는 에러 정보를 받아주는 변수와 이름을 같게 만들어야한다.
		System.out.println("에러 정보" + ex.getMessage());
		System.out.println("에러가 발생 메서드 2...");

		Signature signature = joinPint.getSignature();
		System.out.println(signature.getName());
		System.out.println(signature.toString());

		String body = signature.toString() + "\n" + "에러정보: " + ex.getMessage() + "\n" + "조회수 조작 문제가 발생!! 확인 바람!!";

		try {
			// MIMEMessage 객체 생성
			MimeMessage message = mailSender.createMimeMessage();
			// messager안에 값을 넣어야 하는데, vo 같은 클래스 객체를 만들어서
			// 값을 넣어주어야 함.
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom("mandu0955@gmail.com");
			messageHelper.setTo("mandu0955@gmail.com");
			messageHelper.setSubject("서버 문제 발생!!");
			messageHelper.setText("문제\n" + body);
			mailSender.send(message);
			System.out.println("메일 보냄.");
		} catch (Exception e) {
			System.out.println("메일을 보내는 중 에러발생..." + e);
			// e를 찍어주면 에러정보가 나옴
		}
	}
}
