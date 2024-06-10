package com.aladin.quizzapp.config;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aladin.quizzapp.dto.JwtDTO;
import com.aladin.quizzapp.dto.UserDTO;
import com.aladin.quizzapp.exception.EntityNotFoundException;
import com.aladin.quizzapp.exception.ErrorCodes;
import com.aladin.quizzapp.models.UserEntity;
import com.aladin.quizzapp.repository.JwtRepository;
import com.aladin.quizzapp.services.implementation.UserServiceImplementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


// @Transactional
@AllArgsConstructor
@Transactional
@Service
@Slf4j
public class JWTService {

    private final String ENCRYPTION_KEY = "b053ef49936316c2d0855fb030811894085a5d368b657128d2d0cbcfada785cc";
    private final String BEARER = "bearer";

    private UserServiceImplementation userServiceImplementation;
    private JwtRepository jwtRepository;

    public Map<String, String> generate(String username) {
        UserDTO user = UserDTO.fromEntity((UserEntity) this.userServiceImplementation.loadUserByUsername(username));
        // On desactive tous les tokens de l'utilisateur avant de generer un nouveau
        this.disableTokens(user);


        final Map<String, String> jwtMap = this.generateJWT(user);

        final JwtDTO jwt = JwtDTO
                .builder()
                .isDisable(false)
                .token(jwtMap.get(BEARER))
                .isExpire(false)
                .user(user)
                .build();

        this.jwtRepository.save(JwtDTO.toEntity(jwt));
        return jwtMap;
    }

    public Map<String, String> generateJWT(UserDTO user) {

        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 60 * 60 * 1000;

        final Map<String, Object> claims = Map.of(
                "username", user.getUsername(),
                "email", user.getEmail(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, user.getUsername());

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




    public JwtDTO getJwtDTO(String token) {
        return JwtDTO.fromEntity(this.jwtRepository.findByToken(token).orElseThrow(
                () -> new EntityNotFoundException("The token is not in the database !", ErrorCodes.JWT_NOT_FOUND)));
    }


    
    private void disableTokens(UserDTO user) {

        List<JwtDTO> jwtList = this.jwtRepository.findUserValidTokens(user.getEmail()).stream().map(JwtDTO::fromEntity).peek(
            jwt -> {
                jwt.setDisable(true);
                jwt.setExpire(true);
            }
        ).collect(Collectors.toList());

        
        this.jwtRepository.saveAll(jwtList.stream().map(JwtDTO::toEntity).collect(Collectors.toList()));
    }




    public void logout() {
        UserDTO user = UserDTO.fromEntity((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        JwtDTO jwt = JwtDTO.fromEntity(this.jwtRepository.findUserValidToken(user.getEmail()).orElse(null));
        log.info(jwt.toString());
            
        jwt.setDisable(true);
        jwt.setExpire(true);
        this.jwtRepository.save(JwtDTO.toEntity(jwt));

    }


    // @Scheduled(cron = "* * * * * *") // Toutess les minutes
    // @Scheduled(cron = "0/10 * * * * *") // Toutes les 10 secondes
    @Scheduled(cron = "@daily") // Tous les jours
    public void removeUselessTokens() {

        this.jwtRepository.deleteUselessTokens(true, true);
        log.info("Delete useless tokens !");
    }

} 
