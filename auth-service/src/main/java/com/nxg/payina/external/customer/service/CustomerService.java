package com.nxg.payina.external.customer.service;


import com.nxg.payina.external.customer.dto.CustomerDTO;
import com.nxg.payina.external.customer.dto.LoginDTO;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    String createUser(CustomerDTO customerDto) throws Exception;

    String login(LoginDTO loginDTO) throws Exception;
}
