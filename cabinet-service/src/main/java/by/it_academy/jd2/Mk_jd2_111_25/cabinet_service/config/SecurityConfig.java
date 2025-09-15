package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.config;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception  {

        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(
                                new HttpStatusEntryPoint((HttpStatus.UNAUTHORIZED))
                        )
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.setStatus(HttpStatus.FORBIDDEN.value())))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/cabinet/registration",
                                "/cabinet/verification",
                                "/cabinet/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
