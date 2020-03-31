package com.buk.inzynier.HangMan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.RESET_CONTENT, reason = "You have lost in this game. Please start new one!")
public class GameLostException extends RuntimeException{
}
