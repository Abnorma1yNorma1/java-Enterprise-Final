package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.client.IMailClient;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.MailRequest;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.IMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService implements IMailService {

    private final IMailClient mailClient;

    public void send(MailRequest mailRequest){
        ResponseEntity<?> response = mailClient.sendMail(mailRequest);
        if (!response.getStatusCode().is2xxSuccessful()){
            throw new MailSendException("Request to send mail failed");
        }
    }

}
