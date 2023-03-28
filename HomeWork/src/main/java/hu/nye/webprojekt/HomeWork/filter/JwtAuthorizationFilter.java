package hu.nye.webprojekt.HomeWork.filter;

import hu.nye.webprojekt.HomeWork.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

//@Component
public class JwtAuthorizationFilter /*extends OncePerRequestFilter*/ {


    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            doAuthorization(request);
        } catch (Exception e) {
            LOGGER.error("Authorization error", e);
        }

        filterChain.doFilter(request, response);
    }

     */

    private void doAuthorization(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.split(" ")[1];

            String username = jwtUtil.verifyAndDecodeToken(jwt);

            var userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, Collections.emptyList()
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // We can add additional details to the request object, that we might retrieve and user later on,
            // in different filters or controllers.
            request.setAttribute("authorizedUsername", username);
        }
    }


}
