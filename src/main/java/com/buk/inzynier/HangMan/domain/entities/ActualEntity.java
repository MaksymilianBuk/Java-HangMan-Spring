package com.buk.inzynier.HangMan.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ActualEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String password;

    @JsonIgnore
    @OneToOne
    private PuzzleEntity puzzleEntity;

    private String category;

    private Integer lifeRemaining;

    public ActualEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLifeRemaining() {
        return lifeRemaining;
    }

    public void setLifeRemaining(Integer lifeRemaining) {
        this.lifeRemaining = lifeRemaining;
    }

    public PuzzleEntity getPuzzleEntity() {
        return puzzleEntity;
    }

    public void setPuzzleEntity(PuzzleEntity puzzleEntity) {
        this.puzzleEntity = puzzleEntity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
