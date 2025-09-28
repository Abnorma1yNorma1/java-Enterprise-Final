package by.it_academy.jd2.Mk_jd2_111_25.user_service.controller.filter;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.service.api.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtHandler;
    private final IUserService userService;
    private final HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();

        try {
            if (jwtHandler.validateUserToken(token)) {
                authenticateUser(token);
            } else if (jwtHandler.validateServiceToken(token)) {
                authenticateService();
            } else {
                throw new BadCredentialsException("Invalid token signature or type.");
            }
            filterChain.doFilter(request,response);
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            resolver.resolveException(request, response, null, ex);
//            logger.error("JWT authentication failed: %s\n", ex.getMessage());
        }

    }

    private void authenticateUser(String token) {
        UUID userID = jwtHandler.getUserId(token);
        if (userID != null) {
            UserRole role = userService.findByUuid(userID).getRole();
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
            Authentication auth = new UsernamePasswordAuthenticationToken(userID, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } else {
            SecurityContextHolder.clearContext();
        }
    }

    private void authenticateService() {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_SERVICE"));
        Authentication auth = new UsernamePasswordAuthenticationToken(
                "service-principal",
                null,
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
