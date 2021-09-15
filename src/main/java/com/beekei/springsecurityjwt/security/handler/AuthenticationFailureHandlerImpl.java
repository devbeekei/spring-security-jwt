package com.beekei.springsecurityjwt.security.handler;

import com.beekei.springsecurityjwt.common.ApiResponse;
import com.beekei.springsecurityjwt.common.ApiResponseType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        ApiResponse.error(response, ApiResponseType.UNAUTHORIZED_RESPONSE);
    }

}
