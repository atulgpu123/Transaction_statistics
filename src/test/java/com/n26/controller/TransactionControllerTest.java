package com.n26.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    private ObjectMapper mapper;
    private MockMvc mockMvc;
    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private TransactionController transactionController;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void shouldReturn201HTTPResponse() throws Exception {
        when(transactionService.addTransaction(Mockito.any()))
                .thenReturn(true);

        mockMvc.perform(
                post("/transactions")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(TransactionControllerFixture.transactionWithinAMinute))
        ).andExpect(status().isCreated());

        verify(transactionService, times(1)).addTransaction(any());
    }

    @Test
    public void shouldReturn204HTTPResponse() throws Exception {
        when(transactionService.addTransaction(Mockito.any()))
                .thenReturn(false);

        mockMvc.perform(
                post("/transactions")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(TransactionControllerFixture.transactionOlderThanAMinute))
        ).andExpect(status().isNoContent());

        verify(transactionService, times(1)).addTransaction(any());
    }
}
