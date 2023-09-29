package com.thanksen.osahaneat.unit;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtUtilsHelper {
    private String PrivateKey = "J7AlKlSiWtv2Wvng/hteePlh87HE9mf1yrCbn0y4JE0=";
    Gson gson = new Gson();

    public String generate(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(PrivateKey));
        String jws = Jwts.builder().setSubject(data).signWith(key).compact();
        return jws;
    }

    public Authentication verifyToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(PrivateKey));
            Jws<Claims> decode = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            Claims claims = decode.getBody();
            String data = claims.getSubject();
            DataGenerate dataGenerate = gson.fromJson(data, DataGenerate.class);
            //Create authority
            GrantedAuthority authority = new SimpleGrantedAuthority(dataGenerate.getRoleName());
            //Create authority list
            Collection<GrantedAuthority> rolesName = new ArrayList<>();
            //Add roles
            rolesName.add(authority);
            Authentication authentication = new UsernamePasswordAuthenticationToken(dataGenerate.getUsername(),"",rolesName);
            return authentication;
        } catch (Exception e) {
            return null;
        }
    }


}
