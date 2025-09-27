package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.client;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@FeignClient(name = "user-service", url = "${app.clients.user}")
public interface IUserClient {

    @PostMapping
    ResponseEntity<Void> create(@RequestBody UserInfo userInfo);

    @GetMapping(params = "mail")
    ResponseEntity<UserCreate> getByMail(@RequestParam("mail") String mail);

    @GetMapping("/{uuid}")
    ResponseEntity<User> getByUuid(@PathVariable UUID uuid);

    @GetMapping(params = {"page", "size"})
    ResponseEntity<ResponsePage<User>> get(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size);

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    ResponseEntity<Void> update(@PathVariable UUID uuid,
                                @PathVariable("dt_update") Instant dtUpdate,
                                @RequestBody UserInfo userInfo);
}
