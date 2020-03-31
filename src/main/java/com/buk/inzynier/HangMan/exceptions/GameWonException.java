package com.buk.inzynier.HangMan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.RESET_CONTENT, reason = "You have won this game. Please start new one!")
public class GameWonException extends RuntimeException{
}
