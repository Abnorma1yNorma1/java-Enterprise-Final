package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.common.controller.client;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.controller.utils.JwtTokenHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {

    private final JwtTokenHandler jwtTokenHandler;
    private String serviceToken;
    private Date tokenExpiration;

    @Override
    public void apply(RequestTemplate template) {
        if (!template.headers().containsKey(HttpHeaders.AUTHORIZATION)) {
            if (serviceToken == null || isTokenExpired()) {
                this.serviceToken = jwtTokenHandler.generateServiceToken();
                this.tokenExpiration = jwtTokenHandler.getClaims(serviceToken, "service-secret", "service").getExpiration();
            }
            template.header(HttpHeaders.AUTHORIZATION, "Bearer " + serviceToken);
        }
    }

    private boolean isTokenExpired() {
        return tokenExpiration != null && Instant.now().isAfter(tokenExpiration.toInstant().minusSeconds(60));
    }
}
