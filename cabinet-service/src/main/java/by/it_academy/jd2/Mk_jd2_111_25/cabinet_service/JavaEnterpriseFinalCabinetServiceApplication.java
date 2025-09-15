package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.config.properties.JwtProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
	JwtProperty.class
})
@SpringBootApplication
public class JavaEnterpriseFinalCabinetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEnterpriseFinalCabinetServiceApplication.class, args);
	}

}
