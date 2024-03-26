package com.rasysbox.ws.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.NotFoundException;

import java.io.IOException;

import static com.rasysbox.ws.utils.Shield.blindStr;
import static com.rasysbox.ws.utils.Utilities.getTimestampValue;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> handleException(Exception ex,
                                                            HttpServletRequest request) {
        return new ResponseEntity<>(ExceptionMessage.builder()
                .timestamp(getTimestampValue())
                .message(blindStr("Ocurrió un error en el servicio: " + ex.getMessage()))
                .path(request.getRequestURI())
                .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle not found exception response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> handleNotFoundException(HttpServletRequest request) {
        return new ResponseEntity<>(ExceptionMessage.builder()
                .timestamp(getTimestampValue())
                .message(blindStr("El recurso solicitado no fue encontrado: " + request.getQueryString()))
                .path(request.getRequestURI())
                .build(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle io exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResponseEntity<ExceptionMessage> handleIOException(IOException ex,
                                                              HttpServletRequest request) {
        return new ResponseEntity<>(ExceptionMessage.builder()
                .timestamp(getTimestampValue())
                .message(blindStr("Error de headers: " + ex.getMessage()))
                .path(request.getRequestURI())
                .build(), HttpStatus.NOT_FOUND);
    }

}
