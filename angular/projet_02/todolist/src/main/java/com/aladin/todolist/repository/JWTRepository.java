package com.aladin.todolist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aladin.todolist.model.Jwt;

public interface JWTRepository extends JpaRepository<Jwt, String> {

    Optional<Jwt> findByToken(String token);

    @Query("FROM Jwt j WHERE j.isDisable = false AND j.isExpire = false AND j.user.email = :email")
    Optional<Jwt> findUserValidToken(String email);


    @Query("FROM Jwt j WHERE  j.user.email = :email")
    List<Jwt> findUserValidTokens(String email);

    @Query("FROM Jwt j WHERE  j.refreshToken.token = :token")
    Optional<Jwt> findByRefreshToken(String token);


    @Modifying 
    @Query("DELETE FROM Jwt j WHERE j.isExpire = :isExpire OR j.isDisable = :isDisable")
    void deleteUselessTokens(boolean isExpire, boolean isDisable);
    
}
