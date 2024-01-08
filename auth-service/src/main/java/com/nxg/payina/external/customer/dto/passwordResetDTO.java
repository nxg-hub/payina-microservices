package com.nxg.payina.external.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class passwordResetDTO {


    private String oldPassword;

    private String newPassword;

    private String confirmPassword;

}
