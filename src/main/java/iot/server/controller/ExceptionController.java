package iot.server.controller;

import iot.server.advice.exception.AuthenticationEntryPointException;
import iot.server.model.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public CommonResult entrypointException(){
        throw new AuthenticationEntryPointException();
    }

    @GetMapping("/accessdenied")
    public CommonResult AccessDeniedException(){
        throw new AccessDeniedException("");
    }
}