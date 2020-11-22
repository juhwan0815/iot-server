package iot.server.advice;

import iot.server.advice.exception.*;
import iot.server.model.response.CommonResult;
import iot.server.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request,Exception e){
        return responseService.getFailResult(-1000, "서버 에러");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request,UserNotFoundException e){
        return responseService.getFailResult(-999, "존재하지 않는 회원입니다.");
    }

    @ExceptionHandler(EmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult EmailSigninFaildedException(HttpServletRequest request,EmailSigninFailedException e){
        return responseService.getFailResult(-998,"계정이 존재하지 않거나 이메일 또는 비밀번호가 정확하지 않습니다.");
    }

    @ExceptionHandler(EmailExistedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult EmailExistedException(HttpServletRequest request,EmailExistedException e){
        return responseService.getFailResult(-997,"이미 가입한 계정이 존재합니다.");
    }

    @ExceptionHandler(AuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request,AuthenticationEntryPointException e){
        return responseService.getFailResult(-996,"해당 리소스에 접근하기 위한 권한이 없습니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult accessDeniedException(HttpServletRequest request,AccessDeniedException e){
        return responseService.getFailResult(-995,"보유한 권한으로 접근할 수 없는 리소스입니다.");
    }

    @ExceptionHandler(NotOwnerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult notOwnerException(HttpServletRequest request,NotOwnerException e){
        return responseService.getFailResult(-994,"해당 아이디의 주인이 아닙니다.");
    }
}
