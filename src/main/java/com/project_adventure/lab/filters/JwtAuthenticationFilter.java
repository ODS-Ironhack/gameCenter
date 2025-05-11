package com.project_adventure.lab.filters;

import com.project_adventure.lab.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
    , FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            filterChain.doFilter(request, response);
            return;// pasa al siguiente filtro por si hay endpoints que no están protegidos
        }

        String token = authHeader.substring(7);

        if (!jwtService.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username= jwtService.extractUsername(token);
        String role = jwtService.extractRole(token);

        Collection<GrantedAuthority> authorities = extractAuthorities(role);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("Authenticated as: " + authentication.getAuthorities());
        filterChain.doFilter(request, response);
    }

    private Collection<GrantedAuthority> extractAuthorities(String roleString) {
        if (roleString == null || roleString.isEmpty()) {
            return Collections.emptyList();
        }

        System.out.println("Role string: " + roleString);
        Collection<GrantedAuthority> listRole = List.of(new SimpleGrantedAuthority(roleString.trim()));
        return listRole;
    }
}
