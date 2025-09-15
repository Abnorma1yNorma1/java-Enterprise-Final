package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.IPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEncoder implements IPasswordEncoder {
    
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String hash(String passwordRaw){
        return encoder.encode(passwordRaw);
    }

    @Override
    public boolean match(String passwordRaw, String passwordHash) {
        return encoder.matches(passwordRaw, passwordHash);
    }


}
