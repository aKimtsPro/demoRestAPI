package bstorm.akimts.api.controllers;

import bstorm.akimts.api.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.api.exceptions.ElementNotFoundException;
import bstorm.akimts.api.exceptions.RoleInvalidException;
import bstorm.akimts.api.models.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviser extends ResponseEntityExceptionHandler {

    @ExceptionHandler( ElementAlreadyExistsException.class )
    public ResponseEntity<ErrorDTO> handle(ElementAlreadyExistsException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler( ElementNotFoundException.class )
    public ResponseEntity<ErrorDTO> handle(ElementNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body( new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler( RoleInvalidException.class )
    public ResponseEntity<ErrorDTO> handle(RoleInvalidException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(ex.getMessage()));
    }

//    @ExceptionHandler( MethodArgumentNotValidException.class )
//    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException ex){
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(new ErrorDTO(ex.getMessage()));
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO(ex.getMessage()));
    }
}
