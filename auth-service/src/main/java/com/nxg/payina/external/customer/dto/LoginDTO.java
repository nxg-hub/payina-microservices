package com.nxg.payina.external.customer.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginDTO {

    private String email;
    private String password;

}