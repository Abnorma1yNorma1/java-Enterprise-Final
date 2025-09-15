package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperty {
    private String secret;
    private long expiration;
    private String issuer;
}
