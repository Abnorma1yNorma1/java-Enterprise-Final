package by.it_academy.jd2.Mk_jd2_111_25.mail_service.service.api;

import org.springframework.stereotype.Service;

@Service
public interface IMailService {
    void send(String to, String subject, String text);
}
