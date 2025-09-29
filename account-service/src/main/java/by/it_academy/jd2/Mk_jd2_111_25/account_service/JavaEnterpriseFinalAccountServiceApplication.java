package by.it_academy.jd2.Mk_jd2_111_25.account_service;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.config.properties.JwtProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties({
        JwtProperty.class
})
@SpringBootApplication
public class JavaEnterpriseFinalAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEnterpriseFinalAccountServiceApplication.class, args);
    }
}
