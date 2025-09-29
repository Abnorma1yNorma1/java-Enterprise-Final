package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.common.controller.client;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service", url = "${app.clients.user}", configuration = FeignConfig.class)
public interface IUserClient {

    @GetMapping("/{uuid}")
    ResponseEntity<User> getByUuid(@PathVariable UUID uuid);

}
