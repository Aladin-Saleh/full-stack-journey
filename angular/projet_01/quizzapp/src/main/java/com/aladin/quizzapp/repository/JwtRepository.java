package com.aladin.quizzapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import com.aladin.quizzapp.models.JwtEntity;


public interface JwtRepository extends JpaRepository<JwtEntity, Integer>{
    
    Optional<JwtEntity> findByToken(String token);

    @Query("FROM JwtEntity j WHERE j.isDisable = false AND j.isExpire = false AND j.user.email = :email")
    Optional<JwtEntity> findUserValidToken(String email);


    @Query("FROM JwtEntity j WHERE  j.user.email = :email")
    List<JwtEntity> findUserValidTokens(String email);


    @Modifying 
    @Query("DELETE FROM JwtEntity j WHERE j.isExpire = :isExpire OR j.isDisable = :isDisable")
    void deleteUselessTokens(boolean isExpire, boolean isDisable);


}
