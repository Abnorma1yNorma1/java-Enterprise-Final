package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserLogin;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserRegistration;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.ICabinetService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cabinet")
@RequiredArgsConstructor
public class CabinetController {

    private final ICabinetService cabinetService;
    private final JwtTokenHandler tokenHandler;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistration userRegistration){
        cabinetService.register(userRegistration);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/verification")
    public ResponseEntity<?> verify(@RequestParam String code, @RequestParam String mail){
        cabinetService.verify(code, mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/jwt")
    public ResponseEntity<String> login(@RequestBody @Valid UserLogin userLogin){
        String token = cabinetService.login(userLogin);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/jwt"))
                .body(token);
    }

    @GetMapping("/me")
    @RolesAllowed("USER")
    public ResponseEntity<User> me(Authentication authentication){

        UUID uuid = (UUID) authentication.getPrincipal();
        User user = cabinetService.me(uuid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
