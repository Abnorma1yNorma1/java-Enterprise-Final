package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.filter;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtHandler;
    private final IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        UUID userID = jwtHandler.getId(token);
        UserRole role = userService.getRole(userID);
        List<GrantedAuthority> authorities = List.of( new SimpleGrantedAuthority("ROLE_" + role));
        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userID,
                        null,
                        authorities
                );
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request,response);
    }
}
