package by.it_academy.jd2.Mk_jd2_111_25.user_service.controller;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserInfo;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.service.api.IUserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserInfo userInfo) {
        userService.create(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = {"page", "size"}, /*consumes = "application/json",*/ produces = "application/json")
    public ResponseEntity<ResponsePage<User>> get(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                  @RequestParam(defaultValue = "20") @Min(1) int size) {
        ResponsePage<User> responsePageResponse = userService.get(page, size);
        return new ResponseEntity<>(responsePageResponse, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User> getByUuid(@PathVariable UUID uuid) {
        User user = userService.findByUuid(uuid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserCreate> getByMail(@RequestParam @Email(message = "Invalid email format.") @NotBlank(message = "Mail must be provided.") String mail) {
        UserCreate user = userService.findByMail(mail);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("dt_update") Instant dtUpdate,
                                    @RequestBody UserInfo userInfo) {
        userService.update(uuid, dtUpdate, userInfo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/new")
    public ResponseEntity<UserCreate> serviceCreate(@RequestBody @Valid UserInfo userInfo) {
        UserCreate user = userService.serviceCreate(userInfo);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
