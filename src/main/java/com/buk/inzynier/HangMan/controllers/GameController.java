package com.buk.inzynier.HangMan.controllers;

import com.buk.inzynier.HangMan.domain.entities.ActualEntity;
import com.buk.inzynier.HangMan.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ActualEntity newGame(){
        return gameService.newGame();
    }

    @RequestMapping(path="/level",method = RequestMethod.POST)
    public ActualEntity newGameLevel(@RequestParam("lifes") Integer lifes){
        return gameService.newGameLevel(lifes);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ActualEntity tryLetter(@RequestParam("gameId") Long gameId, @RequestParam("letter") Character character){
        return gameService.tryLetter(gameId,character);
    }

    @RequestMapping(path = "/hint", method = RequestMethod.PUT)
    public ActualEntity hintLetter(@RequestParam("gameId")Long gameId){
        return gameService.hintLetter(gameId);
    }

    @RequestMapping(path = "/answer", method = RequestMethod.PUT)
    public ActualEntity showAnswer(@RequestParam("gameId")Long gameId){
        return gameService.showAnswer(gameId);
    }




}
