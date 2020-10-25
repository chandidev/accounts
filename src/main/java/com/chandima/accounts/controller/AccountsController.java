package com.chandima.accounts.controller;

import com.chandima.accounts.service.AccountService;
import com.chandima.accounts.service.AuthorizatoinService;
import com.chandima.accounts.service.dto.AccountDto;
import com.chandima.accounts.service.dto.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class AccountsController {

    private final AccountService accountService;

    //this will really be done in spring security filter and security context is populated so that
    //the use can be fetched from security context.
    private final AuthorizatoinService authorizatoinService;

    @GetMapping("/accounts")
    public List<AccountDto> getAccounts(HttpServletRequest httpServletRequest) {
        String customerId = getCustomerId(httpServletRequest);

        return accountService.getAccounts(customerId);

    }

    @GetMapping("/accounts/{accountId}/transactions")
    public List<TransactionDto> getTransactions(HttpServletRequest httpServletRequest,
                                                @PathVariable("accountId") String accountId) {
        String customerId = getCustomerId(httpServletRequest);

        return accountService.getTransactions(customerId, accountId);

    }

    private String getCustomerId(HttpServletRequest httpServletRequest) {
        String jwtToken = httpServletRequest.getHeaders("access_token").nextElement();
        return authorizatoinService.getCustomerId(jwtToken);

    }
}
