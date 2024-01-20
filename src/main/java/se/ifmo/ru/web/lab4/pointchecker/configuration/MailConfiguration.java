package se.ifmo.ru.web.lab4.pointchecker.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MailConfig.class)
public class MailConfiguration {
    private final MailConfig mailConfig;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.host());
        mailSender.setPort(mailConfig.port());
        mailSender.setUsername(mailConfig.username());
        mailSender.setPassword(mailConfig.password());
        mailSender.setJavaMailProperties(mailConfig.properties());
        return mailSender;
    }
}
