package com.oceanLife.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private TemplateEngine templateEngine;
	
    @Value("${spring.mail.username}")
    private String fromMail;
	
	public boolean sendCodeMail(String toMail,String code) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(fromMail);
			helper.setTo(toMail);
			helper.setSubject("驗證碼通知");

			// 創建 Thymeleaf context
			Context context = new Context();
			context.setVariable("code", code);

			// Process the template
			String emailContent = templateEngine.process("email-template", context);
			helper.setText(emailContent, true);
			mailSender.send(message);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
