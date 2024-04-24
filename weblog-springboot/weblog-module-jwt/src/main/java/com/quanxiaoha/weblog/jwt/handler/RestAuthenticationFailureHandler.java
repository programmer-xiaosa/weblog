package com.quanxiaoha.weblog.jwt.handler;

import com.quanxiaoha.weblog.common.enums.ResponseCodeEnum;
import com.quanxiaoha.weblog.common.utils.Response;
import com.quanxiaoha.weblog.jwt.exception.UsernameOrPasswordNullException;
import com.quanxiaoha.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 木萨·塔布提
 * @url: blog.arnasoft.site
 * @date: 2023-08-24 15:19
 * @description: 认证失败处理器
 **/
@Component
@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.warn("AuthenticationException: ", exception);
        if (exception instanceof UsernameOrPasswordNullException) {
            // 用户名或密码为空
            ResultUtil.fail(response, Response.fail(exception.getMessage()));
        } else if (exception instanceof BadCredentialsException) {
            // 用户名或密码错误
            ResultUtil.fail(response, Response.fail(ResponseCodeEnum.USERNAME_OR_PWD_ERROR));
        }

        // 登录失败
        ResultUtil.fail(response, Response.fail(ResponseCodeEnum.LOGIN_FAIL));
    }
}
