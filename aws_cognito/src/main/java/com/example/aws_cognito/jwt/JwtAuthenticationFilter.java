package com.example.aws_cognito.jwt;

//import org.springframework.web.filter.OncePerRequestFilter;
//import io.jsonwebtoken.Jwts;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String COGNITO_PUBLIC_KEY_URL = "https://cognito-idp.{region}.amazonaws.com/{poolId}/.well-known/jwks.json";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract and validate JWT Token from the Authorization header.
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // Strip "Bearer " prefix
            // Here, you would decode the JWT and validate it with Cognito's public key
        }
        filterChain.doFilter(request, response);
    }
}

