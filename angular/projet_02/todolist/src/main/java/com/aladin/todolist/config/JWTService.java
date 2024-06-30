package com.aladin.todolist.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aladin.todolist.dto.JwtDTO;
import com.aladin.todolist.dto.RefreshTokenDTO;
import com.aladin.todolist.dto.UserDTO;
import com.aladin.todolist.exception.EntityNotFoundException;
import com.aladin.todolist.exception.ErrorCodes;
import com.aladin.todolist.model.User;
import com.aladin.todolist.repository.JWTRepository;
import com.aladin.todolist.service.implementation.UserServiceImplementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.aladin.todolist.utils.Constant.ENCRYPTION_KEY;
import static com.aladin.todolist.utils.Constant.BEARER;

@AllArgsConstructor
@Transactional
@Service
@Slf4j
public class JWTService {

    private final UserServiceImplementation userService;
    private final JWTRepository jwtRepository;

    public Map<String, String> generate(String username) {

        UserDTO user = UserDTO.fromEntity((User) this.userService.loadUserByUsername(username));

        // On desactive tous les tokens de l'utilisateur avant de generer un nouveau
        this.disableTokens(user);

        final Map<String, String> jwtMap = new HashMap<>(this.generateJWT(user)); //this.generateJWT(user);

        RefreshTokenDTO refreshToken = RefreshTokenDTO.builder()
                .isExpired(false)
                .token(UUID.randomUUID().toString())
                .expirationDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 1 jour 
                .build();

        final JwtDTO jwt = JwtDTO
                .builder()
                .isDisable(false)
                .token(jwtMap.get(BEARER))
                .isExpire(false)
                .refreshToken(refreshToken)
                .user(user)
                .build();

        this.jwtRepository.save(JwtDTO.toEntity(jwt));
        log.info("Token generated !" + refreshToken.getToken());
        jwtMap.put("refreshToken", refreshToken.getToken());
        return jwtMap;
    }

    public Map<String, String> generateJWT(UserDTO user) {

        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 60 * 60 * 1000; // 1 hour

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

        List<JwtDTO> jwtList = this.jwtRepository.findUserValidTokens(user.getEmail()).stream().map(JwtDTO::fromEntity)
                .peek(
                        jwt -> {
                            jwt.setDisable(true);
                            jwt.setExpire(true);
                        })
                .collect(Collectors.toList());

        this.jwtRepository.saveAll(jwtList.stream().map(JwtDTO::toEntity).collect(Collectors.toList()));
    }

    public void logout() {
        UserDTO user = UserDTO.fromEntity((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        JwtDTO jwt = JwtDTO.fromEntity(this.jwtRepository.findUserValidToken(user.getEmail()).orElse(null));
            
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

    public Map<String, String> refreshToken(Map<String, String> refreshToken) {

        // TODO : ajouter un validtor pour verifier que le refresh token est bien present et non expiré et que la date d'expiration est bien dans le futur
        final JwtDTO jwt = JwtDTO.fromEntity(this.jwtRepository.findByRefreshToken(refreshToken.get("refreshToken")).orElseThrow(
                () -> new EntityNotFoundException("The refresh token is not in the database !", ErrorCodes.JWT_NOT_FOUND)
        ));


        Map<String, String> tokens = this.generate(jwt.getUser().getUsername());
        return tokens;

    }

}
