package com.sf.saas.bsp.rule.core.service.config.exceptionhandle;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.sf.saas.bsp.rule.core.common.constans.ResponseCodeEnum;
import com.sf.saas.bsp.rule.core.common.exception.BizException;
import com.sf.saas.bsp.rule.core.common.utils.ResponseHelper;
import com.sf.saas.bsp.rule.core.dto.base.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * @author 01407460
 * @date 2022/9/7 10:10
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle<T> {

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<T> exceptionHandler(Exception e) {
        log.error("exceptionHandler  Exception err is ",e);
      return   ResponseHelper.buildFail();
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Response<T> exceptionHandler(BizException e) {
        log.error("exceptionHandler BizException err is ",e);
        return  ResponseHelper.buildFail(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class, MismatchedInputException.class})
    @ResponseBody
    public Response<T> handleParameterVerificationException(Exception e) {
        log.error(" handle parameter verification exception has been invoked", e);
        String msg = "";
        /// BindException
        if (e instanceof BindException) {
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
            /// MethodArgumentNotValidException
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            // getFieldError获取的是第一个不合法的参数(P.S.如果有多个参数不合法的话)
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
            /// ValidationException 的子类异常ConstraintViolationException
        } else if (e instanceof ConstraintViolationException) {
            /*
             * ConstraintViolationException的e.getMessage()形如
             *     {方法名}.{参数名}: {message}
             *  这里只需要取后面的message即可
             */
            msg = e.getMessage();
            if (msg != null) {
                int lastIndex = msg.lastIndexOf(':');
                if (lastIndex >= 0) {
                    msg = msg.substring(lastIndex + 1).trim();
                }
            }
            /// ValidationException 的其它子类异常
        }
        ResponseCodeEnum codeEnum = ResponseCodeEnum.getCodeEnum(msg);
        return  ResponseHelper.buildFail(codeEnum.getCode(),codeEnum.getMsg());
    }



}
