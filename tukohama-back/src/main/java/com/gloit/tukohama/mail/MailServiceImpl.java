package com.gloit.tukohama.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Mail sendSimpleMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(mail.getAsunto());
        message.setText(mail.getTexto());
        message.setTo(mail.getReceptor());
        message.setSentDate(mail.getEnvio());
        try {
            javaMailSender.send(message);
            System.out.println("Email service -> Email sended");
        } catch (MailException e) {
            e.printStackTrace();

        }
        return mail;
    }

    public Mail sendHtmlMail(Mail mail, String tipo) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHtml = new MimeMessageHelper(message, true);
            String texto="";
            switch (tipo){
                case "verificar":
                    texto= MailHelper.verificarMail(mail.getReceptor(), mail.getTexto());
                    break;
                default:
                    break;
            }
            mailHtml.setFrom(from);
            mailHtml.setTo(mail.getReceptor());
            mailHtml.setSubject(mail.getAsunto());
            mailHtml.setText(texto, true);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            System.out.println("Email service -> FAILED TO SEND MAIL "+e);
        }
        return mail;
    }

    @Override
    public Mail sendHtmlMail(Mail mail) {
        return null;
    }

    @Override
    public Mail sendAttachMail(Mail mail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getReceptor());
            helper.setSubject(mail.getAsunto());
            helper.setText(mail.getTexto(), true);
            helper.addAttachment(mail.getArchivo().getName(), mail.getArchivo());

            javaMailSender.send(message);
            System.out.println("Email service -> Email sended");
        } catch (MessagingException e) {
            System.out.println("Email service -> FAILED TO SEND MAIL "+e);
        }
        return mail;
    }
}