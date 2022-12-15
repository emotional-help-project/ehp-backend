package com.epam.rd.security;

import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.URole;
import com.epam.rd.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    private String secret;
    private int jwtExpirationInMs;
    private int jwtExpirationInMsRememberMe;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.jwtExpirationInMs}")
    public void setJwtExpirationInMs(String jwtExpirationInMs) {
        this.jwtExpirationInMs = Integer.parseInt(jwtExpirationInMs);
    }

    @Value("${jwt.jwtExpirationInMsRememberMe}")
    public void setJwtExpirationInMsRememberMe(String jwtExpirationInMsRememberME) {
        this.jwtExpirationInMsRememberMe = Integer.parseInt(jwtExpirationInMsRememberME);
    }

    // generate token for user
    public String generateToken(Authentication authentication) {

        UserSpecial user = (UserSpecial) authentication.getPrincipal();
        URole role = userRepository.findByEmail(user.getUsername()).get().getRole();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", user.getId());
        claimsMap.put("username", user.getUsername());
        claimsMap.put("role", Collections.singletonList(role));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setClaims(claimsMap)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }


    private String doGenerateToken(Map<String, Object> claims, String subject, boolean rememberMe) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (rememberMe ? jwtExpirationInMsRememberMe : jwtExpirationInMs))).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException ex) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String username = (String) claims.get("username");
        return username;
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String authToken) {

        List<SimpleGrantedAuthority> roles = null;
        Stream<SimpleGrantedAuthority> a = null;

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();

        a = ((List<String>) claims.get("roles", List.class)).stream().map(SimpleGrantedAuthority::new);
        roles = a.collect(Collectors.toList());
        return roles;
    }


}
