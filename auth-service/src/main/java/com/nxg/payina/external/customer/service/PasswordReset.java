package com.nxg.payina.external.customer.service;

import com.nxg.payina.commons.utils.Helper;
import com.nxg.payina.exceptions.UserNotFoundException;
import com.nxg.payina.external.customer.dto.passwordResetDTO;
import com.nxg.payina.external.customer.entity.Customer;
import com.nxg.payina.external.customer.repository.CustomerRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordReset {

    @Autowired
    private final CustomerRepo customerRepo;

    @Autowired
    private final Helper helper;




    public void updatePassword(passwordResetDTO passwordResetDTO, HttpServletRequest request) throws Exception {
        Customer loggedInUser = helper.extractLoggedInCustomer(request);
        Optional<Customer> customer = customerRepo.findByEmail(loggedInUser.getEmail());
        if(customer.isEmpty()){
            throw new UserNotFoundException("User with email does not exist");}
        String password = passwordResetDTO.getOldPassword();
        if(!helper.encoder.matches(password,loggedInUser.getPassword())){
            throw new Exception("Password is incorrect!");
        }
        if (!passwordResetDTO.getNewPassword().equals(passwordResetDTO.getConfirmPassword())){
            throw new Exception("Passwords do not match!");
        }else{

            customer.get().setPassword(helper.encodePassword(passwordResetDTO.getNewPassword()));

            customerRepo.save(customer.get());
        }
    }
}

