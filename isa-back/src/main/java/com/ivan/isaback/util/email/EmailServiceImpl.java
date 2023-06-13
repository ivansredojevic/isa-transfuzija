package com.ivan.isaback.util.email;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@Override
	public String sendLink(EmailDetails details) {
		
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();	
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());
			javaMailSender.send(mailMessage);
			return "Activation email sent";
		} catch (MailException e) {
			return "Error while sending email";
		}
	}

	@Override
	public String sendDeclineEmail(EmailDetails details) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendQrCode(EmailDetails details, String code) throws Exception {
		
		QRCodeWriter barcodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = null;
		try {
			bitMatrix = barcodeWriter.encode(code, BarcodeFormat.QR_CODE, 200, 200);
		} catch (WriterException e) {
			log.error(e.getMessage());
		}
		
	    File qrImg = new File("qr-code.jpg");
	    String name = qrImg.getName();
        log.info(name);
	    BufferedImage bfi = MatrixToImageWriter.toBufferedImage(bitMatrix);
	    ImageIO.write(bfi, "jpg", qrImg);
	   
	    DataSource dataSource = new FileDataSource(qrImg.getPath());
	    
	    MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
        helper.addAttachment(name, dataSource);
        String inlineImage = "<img src=\"cid:" + name + "\"></img><br/>";

        helper.setText(inlineImage + code.toString(), true);
        helper.setSubject(details.getSubject());
        helper.setTo(details.getRecipient());
        helper.setFrom("sokolicc.dji.acc@gmail.com");

        try {
			javaMailSender.send(message);
			return "QR code sent";
		} catch (MailException e) {
			return "Error while sending email";
		}
	}
}
