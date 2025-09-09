package com.markteplace.bazan.markteplace_web.bussines.config;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Properties;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class EmailSender {


    public  void enviarEmail() {
        // Credenciais da sua conta e do servidor SMTP


        final String username = "felipe.bazanflp3005@gmail.com";
        final String password = "jxnfypyxjjrjajgm"; // Use a senha de aplicativo, não a senha principal


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("felipe_bazan3006@hotmail.com"));
            message.setSubject("Venda concretizada");
            message.setText("Venda concluída");

            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}