package com.ivan.isaback.util.email;

import com.ivan.isaback.model.QRCodeModel;

public interface EmailService {
	
	String sendLink(EmailDetails details);
	String sendDeclineEmail(EmailDetails details);
	String sendQrCode(EmailDetails details, QRCodeModel code) throws Exception;
}
