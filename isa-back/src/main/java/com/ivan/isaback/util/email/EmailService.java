package com.ivan.isaback.util.email;

public interface EmailService {
	
	String sendLink(EmailDetails details);
	String sendDeclineEmail(EmailDetails details);
}
