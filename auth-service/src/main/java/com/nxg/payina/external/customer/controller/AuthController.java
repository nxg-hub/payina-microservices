package com.nxg.payina.external.customer.controller;

import com.nxg.payina.commons.utils.Helper;
import com.nxg.payina.external.customer.serviceImpl.CustomerServiceImpl;
import com.nxg.payina.external.customer.service.PasswordReset;
import com.nxg.payina.external.customer.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private final PasswordReset passwordReset;

    @Autowired
    private Helper helper;


    @Autowired
    private final CustomerServiceImpl userService;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws Exception{
        try {
            String token = userService.login(loginDTO);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer "
                            + token)
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                            "Authorization")
                    .build();
            // return ResponseEntity.status(HttpStatus.OK).body("Login successful");

        } catch (Exception e) {
            logger.error("Error while logging in: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());}
    }
}
