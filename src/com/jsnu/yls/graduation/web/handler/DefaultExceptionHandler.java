package com.jsnu.yls.graduation.web.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 默认异常处理
 *
 * Created by WeiXY on 2016/1/23.
 */
@ControllerAdvice
public class DefaultExceptionHandler {


    @ExceptionHandler({IllegalStateException.class})
    public void IllegalStateExceptionHandler(){

    }


}
