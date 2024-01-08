package com.nxg.payina.external.customer.serviceImpl;

import com.nxg.payina.commons.utils.Helper;
import com.nxg.payina.configs.JwtService;
import com.nxg.payina.exceptions.EmailNotValidException;
import com.nxg.payina.exceptions.IncorrectDetailsException;
import com.nxg.payina.exceptions.UserAlreadyExistException;
import com.nxg.payina.external.customer.dto.CustomerDTO;
import com.nxg.payina.external.customer.dto.LoginDTO;
import com.nxg.payina.external.customer.entity.Customer;
import com.nxg.payina.external.customer.repository.CustomerRepo;
import com.nxg.payina.external.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepo customerRepo;

    @Autowired
    private final JwtService jwt;


    @Autowired
    private final Helper helper;
    @Override
    public String createUser(CustomerDTO customerDTO) throws Exception {

        Optional<Customer> existingUser = customerRepo.findByEmail(customerDTO.getEmail());
        if (!helper.isEmailValid(customerDTO.getEmail())) {
            throw new EmailNotValidException("Invalid email address!");
        }
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("Customer with email already exists!");
        }

        Customer customer = new Customer();


        customer.setEmail(customerDTO.getEmail());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setPassword(helper.encodePassword(customerDTO.getPassword()));
        customerRepo.saveAndFlush(customer);
        return "User saved Successfully";


    }

    @Override
    public String login(LoginDTO loginDTO) throws Exception {

        Optional<Customer> customer = customerRepo.findByEmail(loginDTO.getEmail()) ;

        if(!helper.isEmailValid(loginDTO.getEmail())){
            throw new EmailNotValidException("Invalid email address");
        }
        if (customer.isEmpty()) {
            throw new UsernameNotFoundException( "Wrong username or password!");

        }

        if (!helper.isPasswordValid(loginDTO.getPassword(), customer.get().getPassword())){
            throw new IncorrectDetailsException("Wrong username or password!");
        }

        if (!customer.get().isEnabled()) {
            throw new UsernameNotFoundException( "Account is yet to be verified. Kindly confirm your email!");}

        else {
            return jwt.generateToken(customer.get());

        }
    }

}
