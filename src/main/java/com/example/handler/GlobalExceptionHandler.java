package com.example.handler;

import com.example.common.exception.BaseException;
import com.example.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("Error message: {}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
}
