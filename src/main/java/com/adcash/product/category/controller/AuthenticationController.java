package com.adcash.product.category.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adcash.product.category.entity.ResponseStatus;
import com.adcash.product.category.service.SecurityService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * API Endpoint for user authentication
 *
 *
 */
@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseStatus> login(@RequestBody @Valid CredentialsVo credentialsVo) {
        final String username = credentialsVo.getUsername();
        final String password = credentialsVo.getPassword();
        logger.info("About to Authenticate credentials");
        final String token = securityService.authenticate(username, password);
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatus("Success");
		responseStatus.setData(new AuthenticationResponse(token));
        logger.info("User '{}' authenticated successfully -> Token: '{}'", username, token);
        return new ResponseEntity<ResponseStatus>(responseStatus,HttpStatus.OK);
    }

    public static class CredentialsVo {
        @NotNull(message = "username is required")
        private String username;
        @NotNull(message = "password is required")
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class AuthenticationResponse {
        private final String token;

        public AuthenticationResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

}
