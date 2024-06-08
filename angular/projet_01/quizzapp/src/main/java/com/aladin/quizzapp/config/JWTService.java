package com.aladin.quizzapp.config;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;


import org.springframework.stereotype.Service;

import com.aladin.quizzapp.dto.UserDTO;
import com.aladin.quizzapp.models.UserEntity;
import com.aladin.quizzapp.services.implementation.UserServiceImplementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JWTService {

    private final String ENCRYPTION_KEY = "b053ef49936316c2d0855fb030811894085a5d368b657128d2d0cbcfada785cc";
    private UserServiceImplementation userServiceImplementation;

    public Map<String, String> generate(String username) {
        UserDTO user = UserDTO.fromEntity((UserEntity) this.userServiceImplementation.loadUserByUsername(username));
        return this.generateJWT(user);
    }

    public Map<String, String> generateJWT(UserDTO user) {

        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 60 * 60 * 1000;

        final Map<String, Object> claims = Map.of(
                "username", user.getUsername(),
                "email", user.getEmail(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, user.getUsername()
                );

        final String bearer = Jwts.builder()
                .claims()
                .add(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(currentTime))
                .expiration(new Date(expirationTime))
                .and()
                .signWith(getKey())
                .compact();

        return Map.of("bearer", bearer);
    }

    public SecretKey getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaim(token, Claims::getExpiration);

        return expirationDate.before(expirationDate);
    }


    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {

        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
