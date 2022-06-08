package com.shop.services.email;

import java.io.IOException;
import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.shop.dto.email.InfoSendMailDto;
import com.shop.dto.email.UserInfoSendMailDto;
import com.shop.dto.user.UserRequestDto;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class BookingEmailService {

	@Autowired
	protected Configuration configuration;
	
	@Autowired
	protected JavaMailSender sender;
	
	private final static String EMAIL_TEMPLATE_SEND_CONFIRM_USER = "email.ftl";
	
	public void sendMailConfirmUser(UserRequestDto requestDto) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, MessagingException, TemplateException, IOException {
		InfoSendMailDto infoSendMailDto = new InfoSendMailDto();
		infoSendMailDto.setSubject("Send Email confirm create user successfully");
		infoSendMailDto.setSendTo(requestDto.getEmail());
		UserInfoSendMailDto userInfoSendMailDto = new UserInfoSendMailDto();
		userInfoSendMailDto.setUserName(requestDto.getFirstName());
		userInfoSendMailDto.setFullName(requestDto.getEmail());
		infoSendMailDto.setUserInfoSendMailDto(userInfoSendMailDto);
		sendEmail(EMAIL_TEMPLATE_SEND_CONFIRM_USER, infoSendMailDto);
	}
	
	private void sendEmail(String emailTemplate, InfoSendMailDto infoSendMailDto) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, IOException {
		MimeMessage mimeMessage = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setSubject(infoSendMailDto.getSubject());
		helper.setTo(infoSendMailDto.getSendTo());
		String emailContent = getEmailContent(emailTemplate, infoSendMailDto);
		helper.setText(emailContent, true);
		sender.send(mimeMessage);
	}
	
	private String getEmailContent(String emailTemplate, InfoSendMailDto infoSendMailDto) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, TemplateException, IOException {
		StringWriter writer = new StringWriter();
		configuration.getTemplate(emailTemplate).process(infoSendMailDto, writer);
		return writer.getBuffer().toString();
	}
}
