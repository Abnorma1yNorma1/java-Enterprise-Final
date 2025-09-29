package by.it_academy.jd2.Mk_jd2_111_25.classifier_service;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.config.properties.JwtProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties({
        JwtProperty.class
})
@SpringBootApplication
public class JavaEnterpriseFinalClassifierServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaEnterpriseFinalClassifierServiceApplication.class, args);
    }
}
