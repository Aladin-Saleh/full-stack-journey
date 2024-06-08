package com.aladin.quizzapp.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aladin.quizzapp.services.implementation.UserServiceImplementation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTFilter extends OncePerRequestFilter {


    private UserServiceImplementation userServiceImplementation;
    private JWTService jwtService;

    public JWTFilter(UserServiceImplementation userServiceImplementation, JWTService jwtService) {
        this.userServiceImplementation = userServiceImplementation;
        this.jwtService = jwtService;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String username = null;
        Boolean isTokenExpired = true;


        String requestAuth = request.getHeader("Authorization");
        if (StringUtils.hasLength(requestAuth) && requestAuth.startsWith("Bearer ")) {
            token = requestAuth.split(" ")[1];
            isTokenExpired = jwtService.isTokenExpired(token);
            username = this.jwtService.extractUsername(token);

        }

        if (!isTokenExpired && SecurityContextHolder.getContext().getAuthentication() == null && StringUtils.hasLength(username)) {
            UserDetails userDetails = this.userServiceImplementation.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);

    }
    
}
