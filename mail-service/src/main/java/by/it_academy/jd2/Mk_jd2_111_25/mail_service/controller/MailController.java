package by.it_academy.jd2.Mk_jd2_111_25.mail_service.controller;

import by.it_academy.jd2.Mk_jd2_111_25.mail_service.core.dto.MailRequest;
import by.it_academy.jd2.Mk_jd2_111_25.mail_service.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@Valid @RequestBody MailRequest request) {
        emailService.send(request.getTo(), request.getSubject(), request.getText());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
