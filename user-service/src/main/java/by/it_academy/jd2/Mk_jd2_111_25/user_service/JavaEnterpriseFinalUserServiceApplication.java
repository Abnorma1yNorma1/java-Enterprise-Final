package by.it_academy.jd2.Mk_jd2_111_25.user_service;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.config.properties.JwtProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		JwtProperty.class
})
public class JavaEnterpriseFinalUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEnterpriseFinalUserServiceApplication.class, args);
	}

}
