package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.client;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.MailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mail-service", url = "${app.clients.mail}")
public interface IMailClient {

    @PostMapping("/send")
    ResponseEntity<?> sendMail(@RequestBody MailRequest request);
}
