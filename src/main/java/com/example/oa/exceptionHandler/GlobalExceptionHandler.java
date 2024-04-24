package com.example.oa.exceptionHandler;

import com.example.oa.entity.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 全局异常处理程序
 *
 * @author sun
 * @date 2024/04/15
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> handle(ServerException e) {
        return new ResponseEntity<>(R.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
