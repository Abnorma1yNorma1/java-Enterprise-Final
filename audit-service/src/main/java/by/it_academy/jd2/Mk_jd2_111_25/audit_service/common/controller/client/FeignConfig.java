package by.it_academy.jd2.Mk_jd2_111_25.audit_service.common.controller.client;

import by.it_academy.jd2.Mk_jd2_111_25.audit_service.controller.utils.JwtTokenHandler;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor serviceTokenInterceptor(JwtTokenHandler jwtTokenHandler) {
        return new FeignClientInterceptor(jwtTokenHandler);
    }
}