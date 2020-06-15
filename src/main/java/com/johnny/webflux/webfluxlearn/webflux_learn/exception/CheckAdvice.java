package com.johnny.webflux.webfluxlearn.webflux_learn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * @author johnny
 * <p>
 * 统一 异常处理切面
 * @create 2020-03-03 下午2:01
 **/
@ControllerAdvice
public class CheckAdvice {



    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity handleBindException(WebExchangeBindException e) {
        return new ResponseEntity(buildErrorInfo(e), HttpStatus.BAD_REQUEST);
    }

    private String buildErrorInfo(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
                .map(e -> e.getField() + " : " + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

}