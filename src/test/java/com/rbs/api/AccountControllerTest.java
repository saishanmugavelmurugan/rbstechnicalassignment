package com.rbs.api;

import com.rbs.entity.Account;
import com.rbs.entity.Transactions;
import com.rbs.pojo.RestApiResponse;
import com.rbs.pojo.Transaction;
import com.rbs.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    MockMvc mm;
    @MockBean
    AccountService as;

    @DisplayName("get current balance of accounts")
    @Test
    void currentBalance() throws Exception {
        when(as.getBalance(anyString())).thenReturn(new Account(Long.parseLong("1"),"rbs12345",Double.parseDouble("100")));
        MvcResult result= mm.perform(MockMvcRequestBuilders.get("/balance/rbs12345")).andExpect(status().isOk()).andReturn();
        String res=result.getResponse().getContentAsString();
       assertTrue(res.contains("rbs12345"));
        assertTrue(res.contains("100"));
    }

   /** @DisplayName("Account not found")
    @Test
    void accountNotFound() throws Exception {
        when(as.getBalance(anyString())).thenReturn(new RestApiResponse(HttpStatus.NOT_FOUND,"300","Failed","Account Not Found :rbs12345"));
        MvcResult result= mm.perform(MockMvcRequestBuilders.get("/balance/rbs12345")).andReturn();
        String res=result.getResponse().getContentAsString();
        assertTrue(res.contains("rbs12345"));
    }*/
   @DisplayName("get current balance of accounts")
   @Test
   void withDrawRequest() throws Exception {
       when(as.withdrawalAmount(anyDouble(),anyString())).thenReturn(new RestApiResponse(HttpStatus.CREATED,"201","success","WithdrawlTransaction successfull"));
       String requestJson="{\"accountNumber\":\"rbs12345\",\"amount\":10}";
        MvcResult result= mm.perform(post("/withDrawal").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
               .content(requestJson))
               .andDo(print())
               .andExpect(status().isOk()).andReturn();
       String res=result.getResponse().getContentAsString();
       assertTrue(res.contains("201"));
       assertTrue(res.contains("WithdrawlTransaction successfull"));
   }
    @DisplayName("deposit amount")
    @Test
    void depositRequest() throws Exception {
        when(as.depositAmount(anyDouble(),anyString())).thenReturn(new RestApiResponse(HttpStatus.CREATED,"201","success","deposit  successfull"));
        String requestJson="{\"accountNumber\":\"rbs12345\",\"amount\":10}";
        MvcResult result= mm.perform(post("/deposit").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        String res=result.getResponse().getContentAsString();
        //assertTrue(res.contains("201"));
        assertTrue(res.contains("deposit  successfull"));
    }
    @DisplayName("List dr amount")
    @Test
    void dramountRequest() throws Exception {
       List<Transactions> drList=new ArrayList<Transactions>();
       drList.add(new Transactions(Long.parseLong("1"),"rbs12345","Dr","withhDrawal",Double.parseDouble("10")));
        drList.add(new Transactions(Long.parseLong("2"),"rbs12345","Dr","withhDrawal",Double.parseDouble("10")));
        when(as.getCreditOrDebitList(anyString(),anyString())).thenReturn(drList);
        String requestJson="{\"accountNumber\":\"rbs12345\",\"amount\":10}";
        MvcResult result= mm.perform(get("/transactions/dr/rbs12345"))
                .andExpect(status().isOk()).andReturn();
        String res=result.getResponse().getContentAsString();
        assertTrue(res.contains("Dr"));
    }
    @DisplayName("List cr amount")
    @Test
    void cramountRequest() throws Exception {
        List<Transactions> drList=new ArrayList<Transactions>();
        drList.add(new Transactions(Long.parseLong("1"),"rbs12345","Cr","withhDrawal",Double.parseDouble("10")));
        drList.add(new Transactions(Long.parseLong("2"),"rbs12345","cr","withhDrawal",Double.parseDouble("10")));
        when(as.getCreditOrDebitList(anyString(),anyString())).thenReturn(drList);
        String requestJson="{\"accountNumber\":\"rbs12345\",\"amount\":10}";
        MvcResult result= mm.perform(get("/transactions/Cr/rbs12345"))
                .andExpect(status().isOk()).andReturn();
        String res=result.getResponse().getContentAsString();
        assertTrue(res.contains("cr"));
    }
    @DisplayName("addAccount")
    @Test
    void addAccount() throws Exception {

        when(as.createAccount(anyString(),anyDouble())).thenReturn(new RestApiResponse(HttpStatus.CREATED,"201","success","Account created successfully"));

        MvcResult result= mm.perform(get("/addAccount/rbs12345/100"))
                .andExpect(status().isOk()).andReturn();
        String res=result.getResponse().getContentAsString();
        assertTrue(res.contains("201"));
    }
}
