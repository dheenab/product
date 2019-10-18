package com.adcash.product.category.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adcash.product.category.service.SecurityService;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    private static final String HEADER_STRING = "Authorization";

    @Autowired
    private SecurityService securityService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain filterChain)
        throws ServletException, IOException {

        final String token = httpRequest.getHeader(HEADER_STRING);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                securityService.authenticate(token);
            } catch (Exception e) {
                logger.debug("Failed when authenticating token '{}'. Error: '{}'", token, e.getMessage());
            }
        }

        filterChain.doFilter(httpRequest, httpResponse);

    }

	

}