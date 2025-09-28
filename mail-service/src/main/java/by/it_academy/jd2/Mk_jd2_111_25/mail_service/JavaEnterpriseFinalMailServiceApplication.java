package by.it_academy.jd2.Mk_jd2_111_25.mail_service;

import by.it_academy.jd2.Mk_jd2_111_25.mail_service.config.properties.JwtProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		JwtProperty.class
})
public class JavaEnterpriseFinalMailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEnterpriseFinalMailServiceApplication.class, args);
	}

}
