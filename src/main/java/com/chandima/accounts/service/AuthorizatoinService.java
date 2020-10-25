package com.chandima.accounts.service;

import org.springframework.stereotype.Service;

@Service
public class AuthorizatoinService {

    public String getCustomerId(String jwtToken) {
        //in reality this will decode the token, check for signature validity and expiry and get the sub
        //and create a user context.
        // This will be called from Spring security filter in a real scenario.
        return "001";
    }
}
