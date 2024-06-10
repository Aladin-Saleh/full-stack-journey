package com.aladin.quizzapp.models;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

// Il s'agit d'une entité abstraite pour tout les attribut commun

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Permet de mettre à jour createDate et lastModificationDate
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue 
    private Integer id;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    @JsonIgnore
    private Instant createDate;

    @LastModifiedDate 
    @Column(name = "lastUpdate")
    @JsonIgnore
    private Instant lastModificationDate;
    
}
