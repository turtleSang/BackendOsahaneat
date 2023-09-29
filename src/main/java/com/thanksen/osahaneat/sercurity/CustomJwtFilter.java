package com.thanksen.osahaneat.sercurity;

import com.mysql.cj.util.StringUtils;
import com.thanksen.osahaneat.unit.JwtUtilsHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    private JwtUtilsHelper jwtUtilsHelper;

    @Autowired
    public CustomJwtFilter(JwtUtilsHelper jwtUtilsHelper){
        this.jwtUtilsHelper = jwtUtilsHelper;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = this.getTokenFormHeader(request);
        if (token != null){
            Authentication authentication = jwtUtilsHelper.verifyToken(token);
            if (authentication != null){
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
    public String getTokenFormHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token ="";
        if (header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
        }

        return token;
    }
}
