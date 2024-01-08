package com.nxg.payina.external.customer.controller;

import com.nxg.payina.commons.utils.Helper;
import com.nxg.payina.external.customer.service.CustomerService;
import com.nxg.payina.external.customer.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class CustomerController {


        private final Logger logError = LoggerFactory.getLogger(CustomerController.class);

        @Autowired
        private Helper helper;

        @Autowired
        private final CustomerService customerService;

        @PostMapping("/register")
        public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) throws Exception{
            try {
                String response = customerService.createUser(customerDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }catch (Exception e) {

            logError.error("Error creating new Customer: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }


    }
