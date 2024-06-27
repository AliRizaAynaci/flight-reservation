package com.flightreservation.security;


import com.flightreservation.model.enums.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.token.expiration.time}")
    private long TOKEN_EXPIRATION_TIME;


    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + TOKEN_EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .claim("email", userDetails.getEmail())
                .claim("name", userDetails.getName())
                .claim("role", userDetails.getRole().name())
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public String generateJwtTokenByUserId(Long userId) {
        Date expireDate = new Date(new Date().getTime() + TOKEN_EXPIRATION_TIME);
        return Jwts.builder().setSubject(Long.toString(userId))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    // Token'dan kullanıcı ID'sini alır
    public Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    // Token'dan kullanıcı detaylarını alır
    public JwtUserDetails getUserDetailsFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Long userId = Long.parseLong(claims.getSubject());
        String email = claims.get("email", String.class);
        String name = claims.get("name", String.class);
        String phoneNumber = claims.get("phoneNumber", String.class);
        Role role = Role.fromString(claims.get("role", String.class));
        return new JwtUserDetails(userId, email, null, name, phoneNumber, role);
    }

    // Token'ın geçerliliğini kontrol eder
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Token'ın süresinin dolup dolmadığını kontrol eder
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }



}
