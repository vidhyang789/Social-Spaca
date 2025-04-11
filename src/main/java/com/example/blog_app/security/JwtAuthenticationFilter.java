package com.example.blog_app.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("🔍 JWT Filter is executing...");

        // Step 1: Check if Authorization header exists
        String requestToken = request.getHeader("Authorization");
        System.out.println("🔍 Received Authorization Header: " + requestToken);

        String username = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer ")) {
            token = requestToken.substring(7);
            System.out.println("✅ Extracted Token: " + token);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
                System.out.println("✅ Extracted Username: " + username);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Unable to get JWT token");
            } catch (ExpiredJwtException e) {
                System.out.println("❌ Token expired");
            } catch (MalformedJwtException e) {
                System.out.println("❌ Invalid JWT");
            }
        } else {
            System.out.println("⚠️ JWT token is missing or does not start with 'Bearer '");
        }

        // Step 2: Validate Token and Authenticate User
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("✅ Authentication successful for user: " + username);
            } else {
                System.out.println("❌ Token validation failed");
            }
        } else {
            System.out.println("⚠️ Username is null or security context is not null");
        }

        filterChain.doFilter(request, response);
    }

}
