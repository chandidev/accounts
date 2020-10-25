package com.chandima.accounts.controller;

import com.chandima.accounts.exception.UnauthorizedAccessException;
import com.chandima.accounts.service.AccountService;
import com.chandima.accounts.service.AuthorizatoinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class AccountsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountsController accountsController;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AuthorizatoinService authorizatoinService;

    @Test
    public void getAccounts_whenAuthorized_returnsAccounts() throws Exception {
        when(authorizatoinService.getCustomerId(anyString())).thenReturn("001");
        when(accountService.getAccounts("001")).thenReturn(new ArrayList<>());

        MvcResult mvcResult = mvc.perform(get("/accounts").header("access_token", "dd"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }


    @Test
    public void getTransactions_whenAuthrorized_returnTransactions() throws Exception {
        when(authorizatoinService.getCustomerId(anyString())).thenReturn("001");
        when(accountService.getTransactions("001", "XX03")).thenReturn(new ArrayList<>());

        MvcResult mvcResult = mvc.perform(get("/accounts/XX03/transactions")
                .header("access_token", "dd"))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void getTransactions_whenNotAuthrorized_returnTransactions() throws Exception {
        when(authorizatoinService.getCustomerId(anyString())).thenReturn("002");
        when(accountService.getTransactions("002", "XX03"))
                .thenThrow(UnauthorizedAccessException.class);

        MvcResult mvcResult = mvc.perform(get("/accounts/XX03/transactions")
                .header("access_token", "dd"))
                .andReturn();

        assertEquals(401, mvcResult.getResponse().getStatus());
    }

}
