package com.gloit.tukohama.mail;

public interface MailService {

    public Mail sendSimpleMail(Mail mail);

    public Mail sendHtmlMail(Mail mail);

    public Mail sendAttachMail(Mail mail);

}
