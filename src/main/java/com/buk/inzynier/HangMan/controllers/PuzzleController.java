package com.buk.inzynier.HangMan.controllers;


import com.buk.inzynier.HangMan.domain.entities.PuzzleEntity;
import com.buk.inzynier.HangMan.services.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    private PuzzleService puzzleService;

    @Autowired
    public PuzzleController(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createPuzzle(@RequestBody PuzzleEntity puzzleEntity) {puzzleService.savePuzzle(puzzleEntity);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePuzzle(@RequestParam("id") Long id) {
        puzzleService.deletePuzzle(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public PuzzleEntity getPuzzle(@RequestParam("id") Long id) {
        return puzzleService.getPuzzleById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void editPuzzle(@RequestBody PuzzleEntity puzzleEntity) {
        puzzleService.savePuzzle(puzzleEntity);
    }

    @RequestMapping(path = "getAll", method = RequestMethod.GET)
    public List<PuzzleEntity> getPuzzleAllPuzzles() {
        return puzzleService.getAllPuzzles();
    }
}
