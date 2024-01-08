package com.nxg.payina.external.customer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class CustomerDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;


}
