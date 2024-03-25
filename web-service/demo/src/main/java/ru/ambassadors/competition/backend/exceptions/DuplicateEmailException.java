package ru.ambassadors.competition.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Такая почта уже зарегистрирована")
public class DuplicateEmailException extends RuntimeException{
}
