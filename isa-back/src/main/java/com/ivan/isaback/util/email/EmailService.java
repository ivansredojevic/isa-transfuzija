package com.ivan.isaback.util.email;

public interface EmailService {
	
	String sendLink(EmailDetails details);
	String sendDeclineEmail(EmailDetails details);
	String sendQrCode(EmailDetails details, String code) throws Exception;
}
