package com.example.cmc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
//The first spring security related class that we’ll define is JwtAuthenticationEntryPoint.
//It implements AuthenticationEntryPoint interface and provides the implementation for its commence() method.
//This method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication.
//In this case, we’ll simply respond with a 401 error containing the exception message.