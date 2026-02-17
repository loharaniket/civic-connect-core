package com.civic_connect_core.app.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.civic_connect_core.app.services.CustomUserDetailService;
import com.civic_connect_core.app.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private CustomUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Request: " + request.getRequestURI());

        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            System.out.println("Auth Header Null");
            filterChain.doFilter(request, response);
            return;
        }
        
        if (!jwtService.validateToken(authHeader)) {
            System.out.println("Not Valid Token");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Passed All Test Jwt Filter");
        UserDetails userDetails = userDetailService.loadUserByUsername(jwtService.getEmailFromToken(authHeader));
        var authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities());
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        System.out.println("Now Set SecurityContext");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }

}
