package by.it_academy.jd2.Mk_jd2_111_25.mail_service.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperty {

    private Map<String, String> secretsMap;
    private long expiration;
    private String issuer;
}
