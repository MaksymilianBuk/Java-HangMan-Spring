package com.buk.inzynier.HangMan.services;

import com.buk.inzynier.HangMan.domain.entities.PuzzleEntity;
import com.buk.inzynier.HangMan.exceptions.NoPuzzleWithIdException;
import com.buk.inzynier.HangMan.repositories.PuzzleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuzzleService {
    private PuzzleRepository  puzzleRepository;

    @Autowired
    public PuzzleService(PuzzleRepository puzzleRepository) {
        this.puzzleRepository = puzzleRepository;
    }

    public PuzzleEntity getPuzzleById(Long id){
        return puzzleRepository.findById(id).orElseThrow(NoPuzzleWithIdException::new);
    }

    public List<PuzzleEntity> getAllPuzzles(){
        return puzzleRepository.findAll();
    }

    public void savePuzzle(PuzzleEntity puzzleEntity){
        puzzleRepository.saveAndFlush(puzzleEntity);
    }

    public void deletePuzzle(Long id){
        puzzleRepository.deleteById(id);
    }

}
