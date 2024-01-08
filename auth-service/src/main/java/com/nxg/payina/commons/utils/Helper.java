package com.nxg.payina.commons.utils;

import com.nxg.payina.external.customer.entity.Customer;
import com.nxg.payina.external.customer.repository.CustomerRepo;
import com.nxg.payina.configs.JwtService;
import com.nxg.payina.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class Helper<K,V> {


        private final JwtService jwtService;
        private final CustomerRepo customerRepo;


        public final PasswordEncoder encoder = new BCryptPasswordEncoder();

        public Customer extractLoggedInCustomer(HttpServletRequest request){
            final String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            String email = jwtService.extractUsername(jwt);
            return customerRepo.findByEmail(email).orElseThrow(()-> new NotFoundException("Customer not found"));
        }

    public String encodePassword(String password) {

        return encoder.encode(password);
    }

    public boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

        return email != null && pattern.matcher(email).matches();
    }

    public boolean isPasswordValid(String password, String encodedPassword){
        return encoder.matches(password, encodedPassword);
    }

}

