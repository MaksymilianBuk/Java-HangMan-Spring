package com.buk.inzynier.HangMan.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "There is no game with this id")
public class NoGameIdException extends RuntimeException {
}
